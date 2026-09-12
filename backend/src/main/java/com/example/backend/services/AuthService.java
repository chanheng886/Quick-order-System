package com.example.backend.services;

import com.example.backend.dtos.RequestDTO.LoginRequestDTO;
import com.example.backend.dtos.RequestDTO.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTO.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO dto);
    UserResponseDTO login(LoginRequestDTO dto);
}
