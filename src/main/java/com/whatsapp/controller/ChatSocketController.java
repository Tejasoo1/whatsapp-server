package com.whatsapp.controller;

import com.whatsapp.DTO.ChatDTOFE;
import com.whatsapp.DTO.MessageDTOFE;
import com.whatsapp.DTO.OnlineUserRefetchDTO;
import com.whatsapp.DTO.RoomDTO;
import com.whatsapp.DTO.UserDTOFE;
import com.whatsapp.model.OnlineUserRegistry;
import com.whatsapp.model.User;
import java.security.Principal;
import java.util.Map;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class ChatSocketController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ChatSocketController.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final OnlineUserRegistry onlineUserRegistry;

    public ChatSocketController(SimpMessagingTemplate messagingTemplate, OnlineUserRegistry onlineUserRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.onlineUserRegistry = onlineUserRegistry;
    }

    @MessageMapping({"/chat/setup"})
    public void setup(@Payload UserDTOFE user, SimpMessageHeaderAccessor headerAccessor) {
        log.info("User setup: {}", user.getId());
        headerAccessor.getSessionAttributes().put("userId", user.getId());
        this.onlineUserRegistry.addUser(String.valueOf(user.getId()));
        log.info("Online users: {}", this.onlineUserRegistry.getOnlineUsers());
        this.messagingTemplate.convertAndSend("/topic/user/online", user.getId());
        this.messagingTemplate.convertAndSendToUser(String.valueOf(user.getId()), "/queue/onlineUsers", this.onlineUserRegistry.getOnlineUsers());
    }

    @MessageMapping({"/message/refetch"})
    public void refetchMessages(@Payload ChatDTOFE chatDTO, Principal principal) {
        if (principal instanceof Authentication authentication) {
            User currentUser = (User)authentication.getPrincipal();
            log.info("/chat/typing currentUser: {}", currentUser);
            String currentUserId = String.valueOf(currentUser.getId());
            log.info("Refetching messages for chat: {}", chatDTO.getId());

            for(UserDTOFE userDTO : chatDTO.getUsers()) {
                if (!userDTO.getId().toString().equals(currentUserId)) {
                    this.messagingTemplate.convertAndSendToUser(userDTO.getId().toString(), "/queue/refetchMessages", Map.of("refetch", true, "chatId", chatDTO.getId()));
                }
            }

        } else {
            log.warn("WebSocket typing: No authenticated user found.");
        }
    }

    @MessageMapping({"/chat/refetch"})
    public void refetchChats(@Payload ChatDTOFE chatDTO, Principal principal) {
        if (chatDTO == null) {
            log.warn("Received null ChatDTOFE in refetchChats");
        } else if (principal instanceof Authentication) {
            Authentication authentication = (Authentication)principal;
            User currentUser = (User)authentication.getPrincipal();
            log.info("/chat/typing currentUser: {}", currentUser);
            String currentUserId = String.valueOf(currentUser.getId());
            log.info("Refetch requested by user {} for deleted/updated chat {}", currentUserId, chatDTO.getId());
            boolean isDeleted = chatDTO.getChatName() == null;

            for(UserDTOFE userDTO : chatDTO.getUsers()) {
                if (!userDTO.getId().toString().equals(currentUserId)) {
                    this.messagingTemplate.convertAndSendToUser(userDTO.getId().toString(), "/queue/refetchChats", Map.of(isDeleted ? "deletedChat" : "updatedChat", chatDTO));
                }
            }

        } else {
            log.warn("WebSocket typing: No authenticated user found.");
        }
    }

    @MessageMapping({"/chat/refetch/updatedpic"})
    public void refetchChatsAfterPicUpdate(@Payload OnlineUserRefetchDTO dto, Principal principal) {
        log.info("Entered the /user/chat/refetch/updatedpic method");

        try {
            if (!(principal instanceof Authentication)) {
                log.warn("WebSocket: No authenticated user for chat refetch.");
                return;
            }

            Authentication authentication = (Authentication)principal;
            log.info("Refetching chats for online users to load their chats: {}", dto.getOnlineChattedUserIds());

            for(Long userId : dto.getOnlineChattedUserIds()) {
                this.messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/refetchChats", Map.of("refetch", true));
            }
        } catch (Exception e) {
            log.error("❌ Error while refetching chats", e);
        }

    }

    @MessageMapping({"/chat/join"})
    public void joinRoom(@Payload ChatDTOFE chat, SimpMessageHeaderAccessor headerAccessor) {
        log.info("User joined chat room: {}", chat.getId());
        headerAccessor.getSessionAttributes().put("chatRoom", chat.getId());
    }

    @MessageMapping({"/chat/leave"})
    public void leaveRoom(@Payload RoomDTO room, SimpMessageHeaderAccessor headerAccessor) {
        log.info("User left room: {}", room.getRoomId());
        headerAccessor.getSessionAttributes().remove("chatRoom");
    }

    @MessageMapping({"/chat/send"})
    public void sendMessage(@Payload MessageDTOFE messageDTO) {
        log.info("New message received in chat: {}", messageDTO.getChat().getId());
        messageDTO.getChat().getUsers().forEach((userDTO) -> {
            if (!userDTO.getId().equals(messageDTO.getSender().getId())) {
                log.info("Other User ID: {}", userDTO.getId());
                this.messagingTemplate.convertAndSendToUser(userDTO.getId().toString(), "/queue/messages", messageDTO);
            }

        });
    }

    @MessageMapping({"/chat/typing"})
    public void typing(@Payload String roomId, Principal principal) {
        if (principal instanceof Authentication authentication) {
            User currentUser = (User)authentication.getPrincipal();
            log.info("/chat/typing currentUser: {}", currentUser);
            this.messagingTemplate.convertAndSend("/topic/" + roomId + "/typing", currentUser.getId());
        } else {
            log.warn("WebSocket typing: No authenticated user found.");
        }
    }

    @MessageMapping({"/chat/stopTyping"})
    public void stopTyping(@Payload String roomId, Principal principal) {
        if (principal instanceof Authentication authentication) {
            User currentUser = (User)authentication.getPrincipal();
            log.info("/chat/typing currentUser: {}", currentUser);
            this.messagingTemplate.convertAndSend("/topic/" + roomId + "/stopTyping", currentUser.getId());
        } else {
            log.warn("WebSocket typing: No authenticated user found.");
        }
    }
}

