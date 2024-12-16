package com.beautySalon.mapper;

import com.beautySalon.dto.CustomerDto;
import com.beautySalon.model.Customer;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());

        if (customer.getAppointments() != null) {
            dto.setAppointments(customer.getAppointments()
                    .stream()
                    .map(appointment -> {
                        AppointmentDto appointmentDto = new AppointmentDto();
                        appointmentDto.setId(appointment.getId());
                        appointmentDto.setAppointmentDateTime(appointment.getAppointmentDateTime().toString());
                        return appointmentDto;
                    })
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return customer;
    }
}
