package com.whatsapp.repository;

import com.whatsapp.model.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @EntityGraph(
            attributePaths = {"currMessage", "user"}
    )
    List<Notification> findByUser_Id(Long userId);

    @EntityGraph(
            attributePaths = {"user", "currMessage"}
    )
    Optional<Notification> findWithDetailsById(Long id);

    void deleteByCurrMessage_IdIn(List<Long> messageIds);
}
