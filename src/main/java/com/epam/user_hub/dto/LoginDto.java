package com.epam.user_hub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    @NotBlank(message = "User email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
