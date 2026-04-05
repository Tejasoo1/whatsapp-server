package com.whatsapp.controller;

import com.whatsapp.DTO.DeleteMessageRequest;
import com.whatsapp.DTO.MessageDTOFE;
import com.whatsapp.mapper.ChatMapper;
import com.whatsapp.mapper.MessageMapper;
import com.whatsapp.model.Chat;
import com.whatsapp.model.Message;
import com.whatsapp.model.Notification;
import com.whatsapp.model.User;
import com.whatsapp.repository.ChatRepository;
import com.whatsapp.repository.MessageRepository;
import com.whatsapp.repository.NotificationRepository;
import com.whatsapp.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/message"})
public class MessageController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepository messageRepo;
    private final ChatRepository chatRepo;
    private final UserRepository userRepo;
    private final NotificationRepository notificationRepo;

    @PostMapping({"/send"})
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, String> body, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            User sender = (User)authentication.getPrincipal();
            String content = (String)body.get("content");
            Long chatId = Long.valueOf((String)body.get("chatId"));
            if (content != null && chatId != null) {
                Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
                if (chat == null) {
                    return ResponseEntity.badRequest().body("Chat not found");
                } else {
                    Message message = new Message();
                    message.setSender(sender);
                    message.setContent(content);
                    message.setChat(chat);
                    message = (Message)this.messageRepo.save(message);
                    chat.setLatestMessage(message);
                    this.chatRepo.save(chat);
                    Map<String, Object> response = new HashMap();
                    response.put("message", MessageMapper.toDTO(message, true));
                    response.put("updatedChat", ChatMapper.toDTO(chat));
                    return ResponseEntity.ok(response);
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid data passed into the request");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @GetMapping({"/chat/{chatId}"})
    public ResponseEntity<?> getAllMessages(@PathVariable Long chatId) {
        List<Message> messages = this.messageRepo.findByChat_Id(chatId);
        List<MessageDTOFE> messageDTOs = (List)messages.stream().map((message) -> MessageMapper.toDTO(message, true)).collect(Collectors.toList());
        return ResponseEntity.ok(messageDTOs);
    }

    @PostMapping({"/imgmessage"})
    public ResponseEntity<?> sendImageMessage(@RequestBody Map<String, String> body, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            User sender = (User)authentication.getPrincipal();
            String content = (String)body.get("content");
            Long chatId = Long.valueOf((String)body.get("chatId"));
            String imageUrl = (String)body.get("imageURL");
            Chat chat = (Chat)this.chatRepo.findById(chatId).orElse(null);
            if (chat == null) {
                return ResponseEntity.badRequest().body("Chat not found");
            } else {
                Message message = new Message();
                message.setSender(sender);
                message.setContent(content);
                message.setImgUrl(imageUrl);
                message.setChat(chat);
                message = (Message)this.messageRepo.save(message);
                chat.setLatestMessage(message);
                this.chatRepo.save(chat);
                return ResponseEntity.ok(MessageMapper.toDTO(message, true));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @Transactional
    @DeleteMapping({"/deletemessage/{messageId}"})
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId, @RequestBody DeleteMessageRequest requestBody) {
        try {
            log.info("message to be deleted, its ID: {}", messageId);
            log.info("requestBody: {}", requestBody);
            Optional<Message> optionalMessage = this.messageRepo.findById(messageId);
            if (optionalMessage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
            } else {
                Message messageToDelete = (Message)optionalMessage.get();
                Chat chat = messageToDelete.getChat();
                log.info("requestBody.lastMessage: {}", requestBody.isLastMessage());
                List<Notification> notifications = this.notificationRepo.findAll().stream().filter((n) -> n.getCurrMessage().getId().equals(messageId)).toList();
                if (!notifications.isEmpty()) {
                    this.notificationRepo.deleteAll(notifications);
                    log.info("Deleted {} notifications related to messageId {}", notifications.size(), messageId);
                }

                Message prevMessageEntity = null;
                if (requestBody.isLastMessage() && requestBody.getPrevMessObjId() != null) {
                    prevMessageEntity = (Message)this.messageRepo.findById(requestBody.getPrevMessObjId()).orElse(null);
                }

                if (requestBody.isLastMessage()) {
                    chat.setLatestMessage(prevMessageEntity);
                    this.chatRepo.save(chat);
                }

                this.messageRepo.delete(messageToDelete);
                return ResponseEntity.ok(MessageMapper.toDTO(messageToDelete, true));
            }
        } catch (Exception e) {
            log.error("❌ Error while deleting message", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error while deleting the message");
        }
    }

    @Generated
    public MessageController(final MessageRepository messageRepo, final ChatRepository chatRepo, final UserRepository userRepo, final NotificationRepository notificationRepo) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
        this.notificationRepo = notificationRepo;
    }
}

