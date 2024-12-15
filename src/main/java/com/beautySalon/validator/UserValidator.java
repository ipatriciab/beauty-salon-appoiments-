package com.beautySalon.validator;

import com.beautySalon.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            errors.rejectValue("username", "NotEmpty", "Username must not be empty.");
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            errors.rejectValue("password", "NotEmpty", "Password must not be empty.");
        } else if (userDto.getPassword().length() < 6) {
            errors.rejectValue("password", "MinLength", "Password must be at least 6 characters long.");
        }

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            errors.rejectValue("email", "NotEmpty", "Email must not be empty.");
        } else if (!userDto.getEmail().contains("@")) {
            errors.rejectValue("email", "InvalidFormat", "Invalid email format.");
        }

        if (userDto.getRole() == null || userDto.getRole().isEmpty()) {
            errors.rejectValue("role", "NotEmpty", "Role must not be empty.");
        }
    }
}
