package com.whatsapp.mapper;

import com.whatsapp.DTO.RolesDTOFE;
import com.whatsapp.DTO.UserDTOFE;
import com.whatsapp.model.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTOFE toDTO(User user) {
        if (user == null) {
            return null;
        } else {
            UserDTOFE dto = new UserDTOFE();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPic(user.getPic());
            List<RolesDTOFE> roleDTOs = (List)user.getRoles().stream().map((role) -> {
                RolesDTOFE roleDTO = new RolesDTOFE();
                roleDTO.setRoleId((long)role.getRoleId());
                roleDTO.setRoleName(role.getRoleName());
                return roleDTO;
            }).collect(Collectors.toList());
            dto.setRoles(roleDTOs);
            return dto;
        }
    }
}

