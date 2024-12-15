package com.beautySalon.mapper;

import com.beautySalon.dto.AppointmentDto;
import com.beautySalon.model.Appointment;
import com.beautySalon.model.enums.ServiceCategory;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDto toDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setCustomerName(appointment.getCustomer().getName());
        dto.setServiceName(appointment.getServiceName());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime().toString());
        dto.setPrice(appointment.getPrice());
        dto.setNotes(appointment.getNotes());
        dto.setCategory(appointment.getCategory().name()); // Convert enum to string
        return dto;
    }

    public Appointment toEntity(AppointmentDto dto) {
        Appointment appointment = new Appointment();
        appointment.setServiceName(dto.getServiceName());
        appointment.setAppointmentDateTime(java.time.LocalDateTime.parse(dto.getAppointmentDateTime()));
        appointment.setPrice(dto.getPrice());
        appointment.setNotes(dto.getNotes());
        appointment.setCategory(ServiceCategory.valueOf(dto.getCategory())); // Convert string to enum
        return appointment;
    }
}
