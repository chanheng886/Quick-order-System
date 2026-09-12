package com.example.backend.services.Impl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.dtos.RequestDTO.LoginRequestDTO;
import com.example.backend.dtos.RequestDTO.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTO.UserResponseDTO;
import com.example.backend.entities.User;
import com.example.backend.enums.UserRole;
import com.example.backend.mappers.UserMapper;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.AuthService;
import com.example.backend.services.JwtService.JwtService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;


    @Transactional 
    @Override 
    public UserResponseDTO register(RegisterRequestDTO dto){
        if(userRepository.existsByEmail(dto.email())){
            throw new IllegalArgumentException("User with email: " + dto.email() + " is already exists");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(UserRole.USER);

        User save = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return userMapper.toResponse(save, token);
    }

    @Transactional 
    @Override 
    public UserResponseDTO login(LoginRequestDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Emaill & Password"));
        String token = jwtService.generateToken(user);

        return userMapper.toResponse(user, token);
    }
}
