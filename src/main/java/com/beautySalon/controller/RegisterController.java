package com.beautySalon.controller;

import com.beautySalon.dto.UserDto;
import com.beautySalon.service.UserService;
import com.beautySalon.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegisterController {

    // == fields ==
    private final UserService userService;
    private final UserValidator userValidator;
    private static final String USER_DTO = "userDto";

    // == constructor ==
    @Autowired
    public RegisterController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    // == mapping methods ==
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        log.info("getRegisterPage called");
        model.addAttribute(USER_DTO, new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(UserDto userDto, BindingResult bindingResult, Model model) {
        log.info("postRegister called");

        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute(USER_DTO, userDto);
            return "register";
        }

        userService.registerUser(userDto);
        return "redirect:/login";
    }
}
