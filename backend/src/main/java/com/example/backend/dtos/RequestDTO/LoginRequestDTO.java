package com.example.backend.dtos.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
    @NotBlank (message = "Email is required!!")
    @Email (message = "Invalid email, please try again!")
    String email,
    @NotBlank (message = "Password is required!")
    @Size (min = 6, max = 12, message = "Password must be around 6-12 digits")
    String password
) {}
