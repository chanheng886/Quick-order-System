package com.example.backend.dtos.RequestDTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * ProductRequestDTO
 */
public record ProductRequestDTO(
    @NotBlank (message = "Name is required!")
    @Size (min = 5, max = 100, message = "Product name should be greather than 5 or less than 100 digits")
    String name,
    @NotBlank (message = "Description is required!")
    @Size (min = 10, max = 255, message = "Description should be greather then 10 or less than 255 digits")
    String description,
    @NotNull (message = "Price is required!")
    BigDecimal price,
    String imageUrl,
    boolean isAvailable
) {}