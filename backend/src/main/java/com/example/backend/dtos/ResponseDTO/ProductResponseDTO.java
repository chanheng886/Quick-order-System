package com.example.backend.dtos.ResponseDTO;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    String imageUrl,
    boolean isAvailable
) {}