package com.example.backend.controllers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.RequestDTO.LoginRequestDTO;
import com.example.backend.dtos.RequestDTO.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTO.UserResponseDTO;
import com.example.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {
    private final AuthService authService;
    @PostMapping ("/register")
    public UserResponseDTO userRegister(@Valid @RequestBody RegisterRequestDTO dto){
        return authService.register(dto);
    }

    @PostMapping ("/login")
    public UserResponseDTO userLogin(@Valid @RequestBody LoginRequestDTO dto){
        return authService.login(dto);
    }
}