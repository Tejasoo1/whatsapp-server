package com.whatsapp.repository;

import com.whatsapp.model.Chat;
import com.whatsapp.model.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    long deleteByChat(Chat chat);

    @Transactional
    void deleteByChat_Id(Long chatId);

    List<Message> findByChat_Id(Long chatId);
}
