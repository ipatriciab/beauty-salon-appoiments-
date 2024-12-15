package com.beautySalon.model;

import com.beautySalon.model.enums.ServiceCategory;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String serviceName;

    @Enumerated(EnumType.STRING)
    private ServiceCategory category;

    private LocalDateTime appointmentDateTime;

    private Double price;

    private String notes;
}
