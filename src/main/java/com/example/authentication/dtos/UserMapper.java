package com.example.authentication.dtos;

import com.example.authentication.entities.UserEntity;

public class UserMapper {
    public static UserDTO toDTO(UserEntity user) {
        return new UserDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
