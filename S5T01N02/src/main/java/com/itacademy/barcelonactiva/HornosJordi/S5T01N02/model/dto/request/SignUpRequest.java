package com.itacademy.barcelonactiva.HornosJordi.S5T01N02.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotEmpty(message = "Please input a first name")
    private String firstName;
    @NotEmpty(message = "Please input a last name")
    private String lastName;
    @Email(message = "Please input a valid email")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}