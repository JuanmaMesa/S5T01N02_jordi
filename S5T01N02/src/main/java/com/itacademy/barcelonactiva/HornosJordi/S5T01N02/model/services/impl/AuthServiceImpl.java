package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.impl;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.User;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignInRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignUpRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.response.JwtAuthResponse;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository.UserRepository;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.AuthService;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }
}