package com.whatsapp.repository;

import com.whatsapp.model.Chat;
import com.whatsapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c WHERE c.isGroupChat = false AND :user MEMBER OF c.users AND :otherUser MEMBER OF c.users")
    List<Chat> findOneOnOneChat(User user, User otherUser);

    @EntityGraph(
            attributePaths = {"users", "groupAdmin", "latestMessage"}
    )
    List<Chat> findByUsersContainingOrderByUpdatedAtDesc(User user);

    @EntityGraph(
            attributePaths = {"users", "groupAdmin", "latestMessage"}
    )
    Optional<Chat> findWithDetailsById(Long id);
}

