package com.beautySalon.service;

import com.beautySalon.dto.ServiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceManagementService {

    public List<ServiceDto> getAvailableServices() {
        // Dummy implementation for available services
        return List.of(
                new ServiceDto("Haircut", 50.0),
                new ServiceDto("Facial", 80.0)
        );
    }
}
