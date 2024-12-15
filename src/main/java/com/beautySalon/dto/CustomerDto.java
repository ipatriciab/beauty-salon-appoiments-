package com.beautySalon.dto;

import lombok.Data;
import java.util.List;

@Data
public class CustomerDto {

    // == fields ==
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    // Relația cu programările
    private List<AppointmentDto> appointments;
}
