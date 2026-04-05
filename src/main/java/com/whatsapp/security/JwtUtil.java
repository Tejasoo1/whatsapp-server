package com.whatsapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "my-secret-key-my-secret-key-my-secret-key!";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor("my-secret-key-my-secret-key-my-secret-key!".getBytes());

    public String generateToken(String userId) {
        return Jwts.builder().setSubject(userId).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 36000000L)).signWith(SIGNING_KEY, SignatureAlgorithm.HS256).compact();
    }

    public String extractUserId(String token) {
        return ((Claims)Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody()).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("JWT token has expired", e);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("JWT token is unsupported", e);
        } catch (MalformedJwtException e) {
            throw new BadCredentialsException("JWT token is malformed", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("JWT token is null or empty", e);
        }
    }
}

