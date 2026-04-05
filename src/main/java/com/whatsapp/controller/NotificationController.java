package com.whatsapp.controller;

import com.whatsapp.DTO.NotificationDTOFE;
import com.whatsapp.DTO.NotificationDeleteRequest;
import com.whatsapp.mapper.NotificationMapper;
import com.whatsapp.model.Message;
import com.whatsapp.model.Notification;
import com.whatsapp.model.User;
import com.whatsapp.repository.MessageRepository;
import com.whatsapp.repository.NotificationRepository;
import com.whatsapp.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/notification"})
public class NotificationController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);
    private final NotificationRepository notificationRepo;
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    @PostMapping({"/create"})
    public ResponseEntity<?> createNotification(@RequestBody Map<String, String> body) {
        Long messageId = Long.valueOf((String)body.get("messageId"));
        Long userId = Long.valueOf((String)body.get("userId"));
        Message message = (Message)this.messageRepo.findById(messageId).orElse(null);
        User user = (User)this.userRepo.findById(userId).orElse(null);
        if (message != null && user != null) {
            Notification notification = new Notification();
            notification.setCurrMessage(message);
            notification.setUser(user);
            Notification savedNotification = (Notification)this.notificationRepo.save(notification);
            return ResponseEntity.ok(NotificationMapper.toDTO(savedNotification));
        } else {
            return ResponseEntity.badRequest().body("Invalid messageId or userId");
        }
    }

    @GetMapping({"/user/{userId}"})
    public ResponseEntity<?> getNotifications(@PathVariable Long userId) {
        List<Notification> notifications = this.notificationRepo.findByUser_Id(userId);
        List<NotificationDTOFE> notificationDTOs = (List)notifications.stream().map(NotificationMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
    }

    @DeleteMapping({"/notificationdelete"})
    public ResponseEntity<?> deleteNotification(@RequestBody NotificationDeleteRequest request) {
        try {
            Long notifyId = request.getNotifyId();
            Optional<Notification> optionalNotification = this.notificationRepo.findWithDetailsById(notifyId);
            if (!this.notificationRepo.existsById(notifyId)) {
                return ResponseEntity.badRequest().body("Notification not found");
            } else {
                Notification notification = (Notification)optionalNotification.get();
                log.info("Deleted Notification is: {}", notification);
                this.notificationRepo.deleteById(notifyId);
                return ResponseEntity.ok(NotificationMapper.toDTO(notification));
            }
        } catch (Exception e) {
            log.error("❌ Error while deleting notification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error while deleting the notification");
        }
    }

    @Generated
    public NotificationController(final NotificationRepository notificationRepo, final MessageRepository messageRepo, final UserRepository userRepo) {
        this.notificationRepo = notificationRepo;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }
}
