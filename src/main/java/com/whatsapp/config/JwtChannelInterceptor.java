package com.whatsapp.config;

import com.whatsapp.model.User;
import com.whatsapp.repository.UserRepository;
import com.whatsapp.security.JwtUtil;
import io.micrometer.common.lang.NonNull;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(JwtChannelInterceptor.class);
    private final JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepo;

    public JwtChannelInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @NonNull
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = (StompHeaderAccessor)MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand command = accessor.getCommand();

        Message var15;
        try {
            if (StompCommand.CONNECT.equals(command)) {
                String authHeader = accessor.getFirstNativeHeader("Authorization");
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    if (this.jwtUtil.isTokenValid(token)) {
                        String userId = this.jwtUtil.extractUserId(token);
                        User user = (User)this.userRepo.findById(Long.parseLong(userId)).orElse((User)null);
                        if (user == null) {
                            throw new BadCredentialsException("Invalid user details !!");
                        }

                        List<GrantedAuthority> authorities = (List)user.getRoles().stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList());
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, (Object)null, authorities);
                        accessor.setUser(authentication);
                    }
                }
            } else {
                Principal user = accessor.getUser();
                log.info("user is: {}", user);
                if (user instanceof Authentication) {
                    Authentication auth = (Authentication)user;
                    log.info("auth is: {}", auth);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            var15 = message;
        } finally {
            SecurityContextHolder.clearContext();
        }

        return var15;
    }
}
