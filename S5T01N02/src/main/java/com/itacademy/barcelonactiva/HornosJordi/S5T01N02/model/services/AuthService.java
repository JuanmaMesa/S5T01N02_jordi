package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignInRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request.SignUpRequest;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.response.JwtAuthResponse;

public interface AuthService {

    JwtAuthResponse signup(SignUpRequest request);

    JwtAuthResponse signin(SignInRequest request);
}