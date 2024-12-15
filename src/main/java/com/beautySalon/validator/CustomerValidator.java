package com.beautySalon.validator;

import com.beautySalon.dto.CustomerDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;

        if (customerDto.getName() == null || customerDto.getName().isEmpty()) {
            errors.rejectValue("name", "NotEmpty", "Customer name must not be empty.");
        }

        if (customerDto.getEmail() == null || customerDto.getEmail().isEmpty()) {
            errors.rejectValue("email", "NotEmpty", "Email must not be empty.");
        } else if (!customerDto.getEmail().contains("@")) {
            errors.rejectValue("email", "InvalidFormat", "Invalid email format.");
        }

        if (customerDto.getPhone() == null || customerDto.getPhone().isEmpty()) {
            errors.rejectValue("phone", "NotEmpty", "Phone number must not be empty.");
        }
    }
}
