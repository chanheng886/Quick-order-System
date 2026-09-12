package com.example.backend.mappers;
import org.springframework.stereotype.Component;
import com.example.backend.dtos.RequestDTO.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTO.UserResponseDTO;
import com.example.backend.entities.User;
import com.example.backend.enums.UserRole;

import lombok.RequiredArgsConstructor;
@Component 
@RequiredArgsConstructor 
public class UserMapper {
    public User toEntity(RegisterRequestDTO dto){
        if(dto==null){
            return null;
        }
        User user = new User();
        user.setFullName(dto.fullname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(UserRole.USER);

        return user;
    }

    public UserResponseDTO toResponse(User user, String token){
        if(user==null){
            return null;
        }
        return new UserResponseDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getRole() != null ? user.getRole().name() : null,
            token
        );
    }
}
