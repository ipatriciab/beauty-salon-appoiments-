package com.beautySalon.controller;

import com.beautySalon.dto.AppointmentDto;
import com.beautySalon.dto.CustomerDto;
import com.beautySalon.dto.UserHeaderDto;
import com.beautySalon.service.AppointmentService;
import com.beautySalon.service.CustomerService;
import com.beautySalon.service.UserService;
import com.beautySalon.validator.AppointmentDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class AdminController {

    // == fields ==
    private final AppointmentDtoValidator appointmentDtoValidator;
    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final UserService userService;
    private static final String APPOINTMENT_DTO = "appointmentDto";
    private static final String CUSTOMER_DTO = "customerDto";
    private static final String USER_HEADER_DTO = "userHeaderDto";

    // == constructor ==
    @Autowired
    public AdminController(AppointmentDtoValidator appointmentDtoValidator, AppointmentService appointmentService,
                           CustomerService customerService, UserService userService) {
        this.appointmentDtoValidator = appointmentDtoValidator;
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.userService = userService;
    }

    // == mapping methods ==
    @GetMapping("/addAppointment")
    public String getAddAppointment(Model model, Authentication authentication) {
        log.info("getAddAppointment called");
        model.addAttribute(APPOINTMENT_DTO, new AppointmentDto());

        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        return "addAppointment";
    }

    @PostMapping("/addAppointment")
    public String postAddAppointment(Model model, AppointmentDto appointmentDto, BindingResult bindingResult,
                                      Authentication authentication, @RequestParam("details") MultipartFile detailsFile) {

        String loggedUserEmail = authentication.getName();
        appointmentDtoValidator.validate(appointmentDto, bindingResult);

        if (bindingResult.hasErrors()) {
            UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
            model.addAttribute(USER_HEADER_DTO, userHeaderDto);

            model.addAttribute(APPOINTMENT_DTO, appointmentDto);
            return "addAppointment";
        }
        log.info("Appointment added");
        appointmentService.addAppointment(appointmentDto, loggedUserEmail, detailsFile);
        return "redirect:/addAppointment";
    }
}
