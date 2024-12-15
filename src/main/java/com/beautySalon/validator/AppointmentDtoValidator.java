package com.beautySalon.validator;

import com.beautySalon.dto.AppointmentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppointmentDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AppointmentDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppointmentDto appointmentDto = (AppointmentDto) target;

        if (appointmentDto.getServiceName() == null || appointmentDto.getServiceName().isEmpty()) {
            errors.rejectValue("serviceName", "NotEmpty", "Service name must not be empty.");
        }

        if (appointmentDto.getAppointmentDateTime() == null || appointmentDto.getAppointmentDateTime().isEmpty()) {
            errors.rejectValue("appointmentDateTime", "NotEmpty", "Appointment date and time must not be empty.");
        }

        if (appointmentDto.getPrice() == null || appointmentDto.getPrice() <= 0) {
            errors.rejectValue("price", "PositiveValue", "Price must be greater than zero.");
        }
    }
}
