package com.whatsapp.model;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class OnlineUserRegistry {
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    public void addUser(String userId) {
        this.onlineUsers.add(userId);
    }

    public void removeUser(String userId) {
        this.onlineUsers.remove(userId);
    }

    public Set<String> getOnlineUsers() {
        return Collections.unmodifiableSet(this.onlineUsers);
    }
}
