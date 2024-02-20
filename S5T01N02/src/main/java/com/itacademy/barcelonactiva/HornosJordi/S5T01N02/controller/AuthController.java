package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.controller;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignInRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignUpRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.response.JwtAuthResponse;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.AuthService;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.security.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("diceapi/v1/diceGame/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid SignInRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
}