package com.beautySalon.service;

import com.beautySalon.dto.AppointmentDto;
import com.beautySalon.mapper.AppointmentMapper;
import com.beautySalon.model.Appointment;
import com.beautySalon.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AppointmentDto> getUpcomingAppointments(String username) {
        LocalDateTime now = LocalDateTime.now();
        return appointmentRepository.findByAppointmentDateTimeBetween(now, now.plusWeeks(1))
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto)
                .orElse(null);
    }

    public void addAppointment(AppointmentDto appointmentDto, String createdBy, MultipartFile detailsFile) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDto);
        appointmentRepository.save(appointment);
    }

    public void updateAppointment(Long id, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setAppointmentDateTime(LocalDateTime.parse(appointmentDto.getAppointmentDateTime()));
        appointment.setNotes(appointmentDto.getNotes());
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    
    public void sendDailyReminders() {
        // Logica pentru trimiterea mementourilor zilnice.
        System.out.println("Daily reminders sent to customers!");
    }
}
