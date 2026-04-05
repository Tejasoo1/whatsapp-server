package com.whatsapp.mapper;

import com.whatsapp.DTO.ChatDTOFE;
import com.whatsapp.DTO.MessageDTOFE;
import com.whatsapp.model.Message;

public class MessageMapper {
    public static MessageDTOFE toDTO(Message message, boolean includeChat) {
        if (message == null) {
            return null;
        } else {
            MessageDTOFE dto = new MessageDTOFE();
            dto.setId(message.getId());
            dto.setContent(message.getContent());
            dto.setImgUrl(message.getImgUrl());
            dto.setSender(UserMapper.toDTO(message.getSender()));
            dto.setCreatedAt(message.getCreatedAt());
            dto.setUpdatedAt(message.getUpdatedAt());
            if (includeChat) {
                dto.setChat(ChatMapper.toDTO(message.getChat()));
            } else {
                dto.setChat((ChatDTOFE)null);
            }

            return dto;
        }
    }
}
