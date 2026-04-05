package com.whatsapp.config;

import com.whatsapp.model.OnlineUserRegistry;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(WebSocketDisconnectListener.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final OnlineUserRegistry onlineUserRegistry;

    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = String.valueOf(accessor.getSessionAttributes().get("userId"));
        if (userId != null) {
            this.onlineUserRegistry.removeUser(userId);
            this.messagingTemplate.convertAndSend("/topic/user/offline", userId);
            log.info("User {} disconnected. Online users: {}", userId, this.onlineUserRegistry.getOnlineUsers());
        }

    }

    @Generated
    public WebSocketDisconnectListener(final SimpMessagingTemplate messagingTemplate, final OnlineUserRegistry onlineUserRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.onlineUserRegistry = onlineUserRegistry;
    }
}

