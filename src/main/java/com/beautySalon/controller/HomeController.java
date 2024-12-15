package com.beautySalon.controller;

import com.beautySalon.dto.AppointmentDto;
import com.beautySalon.dto.ServiceDto;
import com.beautySalon.dto.UserHeaderDto;
import com.beautySalon.service.AppointmentService;
import com.beautySalon.service.ServiceManagementService;
import com.beautySalon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    // == fields ==
    private final AppointmentService appointmentService;
    private final ServiceManagementService serviceManagementService;
    private final UserService userService;
    private static final String USER_HEADER_DTO = "userHeaderDto";

    // == constructor ==
    @Autowired
    public HomeController(AppointmentService appointmentService, ServiceManagementService serviceManagementService,
                          UserService userService) {
        this.appointmentService = appointmentService;
        this.serviceManagementService = serviceManagementService;
        this.userService = userService;
    }

    // == mapping methods ==
    @GetMapping("/home")
    public String getHomePage(Model model, Authentication authentication) {
        log.info("getHomePage called");

        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        List<AppointmentDto> upcomingAppointments = appointmentService.getUpcomingAppointments(authentication.getName());
        model.addAttribute("upcomingAppointments", upcomingAppointments);

        List<ServiceDto> availableServices = serviceManagementService.getAllServices();
        model.addAttribute("availableServices", availableServices);

        return "home";
    }
}
