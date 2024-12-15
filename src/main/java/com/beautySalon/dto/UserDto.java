package com.beautySalon.dto;

import com.beautySalon.model.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    // == fields ==
    private Long id;
    private String username;
    private String password;
    private String email;

    // Enum pentru rolul utilizatorului
    private Role role;
}
