package com.whatsapp.security;

import com.whatsapp.model.Roles;
import com.whatsapp.model.User;
import com.whatsapp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepo;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if (request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    log.info("✅ JWT token found in cookie");
                }
            }
        } else {
            log.warn("❌ No cookies found in request");
        }

        if (token != null) {
            try {
                String userId = this.jwtUtil.extractUserId(token);
                log.info("✅ Extracted userId from token: {}", userId);
                User user = (User)this.userRepo.findById(Long.parseLong(userId)).orElse((User)null);
                if (user != null) {
                    log.info("✅ Authenticated user: {}", user.getEmail());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, (Object)null, this.getGrantedAuthorities(user.getRoles()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.warn("❌ No user found for ID: {}", userId);
                }
            } catch (Exception e) {
                log.error("❌ JWT token processing failed", e);
            }
        } else {
            log.warn("❌ No JWT token found in request cookies");
        }

        filterChain.doFilter(request, response);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Roles> rolesList) {
        return (List)rolesList.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList());
    }
}
