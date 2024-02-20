package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.impl;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.exception.PlayerNotFoundException;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository.UserRepository;
import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new PlayerNotFoundException("User not found"));
            }
        };
    }
}