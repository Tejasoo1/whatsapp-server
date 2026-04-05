package com.whatsapp.controller;

import com.whatsapp.DTO.ChangePicRequest;
import com.whatsapp.model.Roles;
import com.whatsapp.model.User;
import com.whatsapp.repository.RolesRepository;
import com.whatsapp.repository.UserRepository;
import com.whatsapp.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/user"})
public class UserController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping({"/register"})
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user, HttpServletResponse response, Errors errors) {
        if (errors.hasErrors()) {
            Map<String, String> errorMap = new HashMap();
            errors.getFieldErrors().forEach((fieldError) -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
            log.warn("Errors in the User Entity Validations: {}", errorMap);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        } else if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            Map<String, String> errorExists = new HashMap();
            errorExists.put("email", "User with this email already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorExists);
        } else {
            Optional<Roles> userRole = Optional.ofNullable(this.rolesRepository.getByRoleName("USER"));
            if (userRole.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("USER role not found in the system");
            } else {
                user.setRoles(Collections.singletonList((Roles)userRole.get()));
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                User savedUser = (User)this.userRepository.save(user);
                String token = this.jwtUtil.generateToken(savedUser.getId().toString());
                Cookie cookie = new Cookie("token", token);
                cookie.setAttribute("SameSite", "None");
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                Map<String, Object> responseBody = new HashMap();
                responseBody.put("user", savedUser);
                responseBody.put("token", token);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
            }
        }
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String email = (String)body.get("email");
        String password = (String)body.get("password");
        Optional<User> userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email !!!");
        } else {
            User user = (User)userOpt.get();
            if (!this.passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password !!!");
            } else {
                String token = this.jwtUtil.generateToken(user.getId().toString());
                Cookie cookie = new Cookie("token", token);
                cookie.setAttribute("SameSite", "None");
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                Map<String, Object> responseBody = new HashMap();
                responseBody.put("user", user);
                responseBody.put("token", token);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
            }
        }
    }

    @GetMapping({"/logout"})
    public ResponseEntity<?> logout(HttpServletResponse response) {
        log.info("logout method was called");
        Cookie cookie = new Cookie("token", "");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping({"/guest"})
    public ResponseEntity<?> guestUser(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String email = (String)body.get("email");
        String password = (String)body.get("password");
        Optional<User> guest = this.userRepository.findByEmail(email);
        if (guest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Guest Email");
        } else {
            User user = (User)guest.get();
            if (!this.passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Guest Password");
            } else {
                String token = this.jwtUtil.generateToken(user.getId().toString());
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setAttribute("SameSite", "None");
                response.addCookie(cookie);
                Map<String, Object> responseBody = new HashMap();
                responseBody.put("user", user);
                responseBody.put("token", token);
                return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
            }
        }
    }

    @GetMapping
    public ResponseEntity<?> allUsers(@RequestParam(value = "search",required = false) String search, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() != null) {
            User currentUser = (User)authentication.getPrincipal();
            String currentUserId = currentUser.getId().toString();
            log.info("allUsers Method: {}", currentUserId);
            List<User> users;
            if (search != null && !search.isEmpty()) {
                users = this.userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search);
            } else {
                users = this.userRepository.findAll();
            }

            List<User> filteredUsers = users.stream().filter((user) -> !user.getId().toString().equals(currentUserId)).peek((user) -> user.setPassword((String)null)).toList();
            return ResponseEntity.ok(filteredUsers);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PostMapping({"/changepic"})
    public ResponseEntity<?> changeProfilePic(@RequestBody ChangePicRequest request) {
        Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            log.info("changeProfilePic Controller Method was called");
            User user = (User)optionalUser.get();
            user.setPic(request.getImageURL());
            User updatedUser = (User)this.userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }
    }

    @Generated
    public UserController(final UserRepository userRepository, final RolesRepository rolesRepository, final JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.jwtUtil = jwtUtil;
    }
}
