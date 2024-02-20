package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.repository;

import com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}