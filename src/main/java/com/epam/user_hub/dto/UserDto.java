package com.epam.user_hub.dto;

import com.epam.user_hub.entity.Address;
import com.epam.user_hub.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private long uid;
    @NotBlank(message = "User email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Age is mandatory")
    private int age;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @NotBlank(message = "Tech Stack is mandatory")
    private String techStack;
    @NotBlank(message = "Reporting Manager is mandatory")
    private String reportingManager;
    private List<Address> addresses;
    private List<Role> roles;
}
