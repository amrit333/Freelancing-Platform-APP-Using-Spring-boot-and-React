package com.freelancing.platform.service;

import com.freelancing.platform.dto.request.LoginRequest;
import com.freelancing.platform.dto.request.RegisterRequest;
import com.freelancing.platform.dto.response.JwtAuthResponse;
import com.freelancing.platform.entity.User;
import com.freelancing.platform.exception.BadRequestException;
import com.freelancing.platform.repository.UserRepository;
import com.freelancing.platform.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String register(RegisterRequest registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("Email is already registered!");
        }

        User user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(registerDto.getRole())
                .build();

        userRepository.save(user);

        return "User registered successfully.";
    }

    public JwtAuthResponse login(LoginRequest loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return new JwtAuthResponse(token);
    }
}
