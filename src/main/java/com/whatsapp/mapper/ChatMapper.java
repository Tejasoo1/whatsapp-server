package com.whatsapp.mapper;

import com.whatsapp.DTO.ChatDTOFE;
import com.whatsapp.DTO.UserDTOFE;
import com.whatsapp.model.Chat;
import java.util.List;
import java.util.stream.Collectors;

public class ChatMapper {
    public static ChatDTOFE toDTO(Chat chat) {
        if (chat == null) {
            return null;
        } else {
            ChatDTOFE dto = new ChatDTOFE();
            dto.setId(chat.getId());
            dto.setChatName(chat.getChatName());
            dto.setIsGroupChat(chat.isGroupChat());
            List<UserDTOFE> userDTOs = (List)chat.getUsers().stream().map(UserMapper::toDTO).collect(Collectors.toList());
            dto.setUsers(userDTOs);
            dto.setGroupAdmin(UserMapper.toDTO(chat.getGroupAdmin()));
            dto.setLatestMessage(MessageMapper.toDTO(chat.getLatestMessage(), false));
            return dto;
        }
    }
}

