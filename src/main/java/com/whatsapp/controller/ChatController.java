package com.whatsapp.controller;

import com.whatsapp.DTO.ChatDTOFE;
import com.whatsapp.DTO.GroupChatRequestDTO;
import com.whatsapp.mapper.ChatMapper;
import com.whatsapp.model.Chat;
import com.whatsapp.model.Message;
import com.whatsapp.model.User;
import com.whatsapp.repository.ChatRepository;
import com.whatsapp.repository.MessageRepository;
import com.whatsapp.repository.NotificationRepository;
import com.whatsapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/chat"})
public class ChatController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private NotificationRepository notificationRepo;

    @PostMapping({"/access"})
    public ResponseEntity<?> accessChat(@RequestBody Map<String, Long> body, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            User currentUser = (User)authentication.getPrincipal();
            Long userId = (Long)body.get("userId");
            if (userId == null) {
                return ResponseEntity.badRequest().body("userId param not sent with the request.");
            } else {
                User otherUser = (User)this.userRepo.findById(userId).orElse(null);
                if (otherUser == null) {
                    return ResponseEntity.badRequest().body("User not found.");
                } else {
                    List<Chat> chats = this.chatRepo.findOneOnOneChat(currentUser, otherUser);
                    if (!chats.isEmpty()) {
                        return ResponseEntity.status(201).body((Chat)chats.get(0));
                    } else {
                        Chat chat = new Chat();
                        chat.setChatName("sender");
                        chat.setIsGroupChat(false);
                        chat.setUsers(List.of(currentUser, otherUser));
                        chat.setUpdatedAt(LocalDateTime.now());
                        this.chatRepo.save(chat);
                        return ResponseEntity.ok(ChatMapper.toDTO(chat));
                    }
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @GetMapping({"/allchats"})
    public ResponseEntity<?> fetchChats(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            User currentUser = (User)authentication.getPrincipal();
            log.info("fetchChats method called");
            List<Chat> chats = this.chatRepo.findByUsersContainingOrderByUpdatedAtDesc(currentUser);
            List<ChatDTOFE> chatDTOs = (List)chats.stream().map(ChatMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok(chatDTOs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PostMapping({"/group"})
    public ResponseEntity<?> createGroupChat(@RequestBody GroupChatRequestDTO request, @AuthenticationPrincipal User currentUser) {
        try {
            String name = request.getName();
            List<Long> userIds = request.getUsers();
            log.info("userIds are: {}", userIds);
            if (name != null && userIds != null) {
                if (userIds.size() < 1) {
                    return ResponseEntity.badRequest().body("More than 2 users are required for a group chat.");
                } else {
                    List<User> users = this.userRepo.findAllById(userIds.stream().map(Long::valueOf).toList());
                    users.add(currentUser);
                    Chat chat = new Chat();
                    chat.setChatName(name);
                    chat.setIsGroupChat(true);
                    chat.setUsers(users);
                    chat.setGroupAdmin(currentUser);
                    chat.setUpdatedAt(LocalDateTime.now());
                    this.chatRepo.save(chat);
                    return ResponseEntity.ok(ChatMapper.toDTO(chat));
                }
            } else {
                return ResponseEntity.badRequest().body("Please fill all fields.");
            }
        } catch (Exception e) {
            log.error("❌ Error while deleting message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error while creating the group chat");
        }
    }

    @PutMapping({"/rename"})
    public ResponseEntity<?> renameGroupChat(@RequestBody Map<String, Object> body) {
        Long chatId = Long.valueOf(body.get("chatId").toString());
        String chatName = body.get("chatName").toString();
        Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
        if (chat == null) {
            return ResponseEntity.status(404).body("Chat not found.");
        } else {
            chat.setChatName(chatName);
            this.chatRepo.save(chat);
            ChatDTOFE UpdatedChatDTO = ChatMapper.toDTO(chat);
            return ResponseEntity.ok(UpdatedChatDTO);
        }
    }

    @PutMapping({"/add"})
    public ResponseEntity<?> addToGroup(@RequestBody Map<String, Long> body) {
        Long chatId = (Long)body.get("chatId");
        Long userId = (Long)body.get("userId");
        Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
        User user = (User)this.userRepo.findById(userId).orElse(null);
        if (chat != null && user != null) {
            chat.getUsers().add(user);
            this.chatRepo.save(chat);
            ChatDTOFE UpdatedChatDTO = ChatMapper.toDTO(chat);
            return ResponseEntity.ok(UpdatedChatDTO);
        } else {
            return ResponseEntity.status(404).body("Chat or User not found.");
        }
    }

    @PutMapping({"/remove"})
    public ResponseEntity<?> removeFromGroup(@RequestBody Map<String, Long> body) {
        Long chatId = (Long)body.get("chatId");
        Long userId = (Long)body.get("userId");
        Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
        if (chat == null) {
            return ResponseEntity.status(404).body("Chat not found.");
        } else {
            chat.getUsers().removeIf((user) -> user.getId().equals(userId));
            this.chatRepo.save(chat);
            ChatDTOFE UpdatedChatDTO = ChatMapper.toDTO(chat);
            return ResponseEntity.ok(UpdatedChatDTO);
        }
    }

    @DeleteMapping({"/deletegroup"})
    public ResponseEntity<?> deleteGroup(@RequestBody Map<String, Long> body) {
        Long chatId = (Long)body.get("chatId");
        Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
        if (chat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chat not found");
        } else {
            if (chat.getLatestMessage() != null) {
                chat.setLatestMessage((Message)null);
                this.chatRepo.save(chat);
            }

            List<Message> messagesToDelete = this.messageRepo.findByChat_Id(chatId);
            List<Long> messageIds = messagesToDelete.stream().map(Message::getId).toList();
            this.notificationRepo.deleteByCurrMessage_IdIn(messageIds);
            this.messageRepo.deleteAll(messagesToDelete);
            ChatDTOFE deletedChatDTO = ChatMapper.toDTO(chat);
            this.chatRepo.delete(chat);
            return ResponseEntity.ok(deletedChatDTO);
        }
    }

    @DeleteMapping({"/deletechat"})
    @Transactional
    public ResponseEntity<?> deleteAChat(@RequestBody Map<String, Long> body) {
        Long chatId = (Long)body.get("chatId");
        Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
        if (chat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chat not found");
        } else {
            if (chat.getLatestMessage() != null) {
                chat.setLatestMessage((Message)null);
                this.chatRepo.save(chat);
            }

            List<Message> messagesToDelete = this.messageRepo.findByChat_Id(chatId);
            List<Long> messageIds = messagesToDelete.stream().map(Message::getId).toList();
            this.notificationRepo.deleteByCurrMessage_IdIn(messageIds);
            this.messageRepo.deleteAll(messagesToDelete);
            ChatDTOFE deletedChatDTO = ChatMapper.toDTO(chat);
            this.chatRepo.delete(chat);
            return ResponseEntity.ok(Map.of("message", "Chat and associated messages deleted", "deletedChatDoc", deletedChatDTO));
        }
    }
}

