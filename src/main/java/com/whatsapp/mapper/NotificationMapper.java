package com.whatsapp.mapper;

import com.whatsapp.DTO.NotificationDTOFE;
import com.whatsapp.model.Notification;

public class NotificationMapper {
    public static NotificationDTOFE toDTO(Notification notification) {
        if (notification == null) {
            return null;
        } else {
            NotificationDTOFE dto = new NotificationDTOFE();
            dto.setId(notification.getId());
            dto.setUser(UserMapper.toDTO(notification.getUser()));
            dto.setCurrMessage(MessageMapper.toDTO(notification.getCurrMessage(), true));
            return dto;
        }
    }
}

