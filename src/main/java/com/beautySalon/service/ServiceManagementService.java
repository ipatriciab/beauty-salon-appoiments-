package com.beautySalon.service;

import com.beautySalon.dto.ServiceDto;
import com.beautySalon.model.Service;
import com.beautySalon.repository.ServiceRepository;
import com.beautySalon.mapper.ServiceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceManagementService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceManagementService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    // Metodă care returnează toate serviciile din baza de date
    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll() // Presupune că ServiceRepository are findAll()
                .stream()
                .map(serviceMapper::toDto) // Mapare la DTO folosind un mapper
                .collect(Collectors.toList());
    }
}
