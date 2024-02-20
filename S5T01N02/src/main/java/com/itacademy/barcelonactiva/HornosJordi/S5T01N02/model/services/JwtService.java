package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public String extractUsername(String jwt);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public String generateToken(UserDetails userDetails);
}