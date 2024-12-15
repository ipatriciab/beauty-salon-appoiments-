package com.beautySalon.controller;

import com.beautySalon.dto.AppointmentDto;
import com.beautySalon.dto.UserHeaderDto;
import com.beautySalon.service.AppointmentService;
import com.beautySalon.service.UserService;
import com.beautySalon.validator.AppointmentDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AppointmentController {

    // == fields ==
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final AppointmentDtoValidator appointmentDtoValidator;
    private static final String APPOINTMENT_DTO = "appointmentDto";
    private static final String USER_HEADER_DTO = "userHeaderDto";

    // == constructor ==
    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService,
                                  AppointmentDtoValidator appointmentDtoValidator) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.appointmentDtoValidator = appointmentDtoValidator;
    }

    // == mapping methods ==
    @GetMapping("/appointments")
    public String getAllAppointments(Model model, Authentication authentication) {
        log.info("getAllAppointments called");

        model.addAttribute("appointments", appointmentService.getAllAppointments());
        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        return "appointments";
    }

    @GetMapping("/appointment/{id}")
    public String getAppointmentDetails(@PathVariable Long id, Model model, Authentication authentication) {
        log.info("getAppointmentDetails called for appointment ID: {}", id);

        AppointmentDto appointmentDto = appointmentService.getAppointmentById(id);
        if (appointmentDto == null) {
            return "redirect:/appointments";
        }

        model.addAttribute(APPOINTMENT_DTO, appointmentDto);
        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        return "appointmentDetails";
    }

    @PostMapping("/appointment/{id}")
    public String updateAppointment(@PathVariable Long id, AppointmentDto appointmentDto, BindingResult bindingResult,
                                     Authentication authentication, Model model) {
        log.info("updateAppointment called for appointment ID: {}", id);

        appointmentDtoValidator.validate(appointmentDto, bindingResult);

        if (bindingResult.hasErrors()) {
            UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
            model.addAttribute(USER_HEADER_DTO, userHeaderDto);

            model.addAttribute(APPOINTMENT_DTO, appointmentDto);
            return "appointmentDetails";
        }

        appointmentService.updateAppointment(id, appointmentDto);
        return "redirect:/appointments";
    }

    @PostMapping("/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        log.info("deleteAppointment called for appointment ID: {}", id);
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
