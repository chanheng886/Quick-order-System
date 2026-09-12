package com.example.backend.dtos.ResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String fullName,
    String email,
    LocalDateTime createdAt,
    String role,
    String token
) {}
