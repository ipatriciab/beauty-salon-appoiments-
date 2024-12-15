package com.beautySalon.dto;

import lombok.Data;

@Data
public class AppointmentDto {

    // == fields ==
    private Long id;
    private String customerName;
    private String serviceName;
    private String appointmentDateTime;
    private Double price;
    private String notes;
    private String base64Image; // Imagine asociată, opțional
    private String category; // Enum ServiceCategory as string
}
