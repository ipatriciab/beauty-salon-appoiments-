package com.beautySalon.controller;

import com.beautySalon.dto.CustomerDto;
import com.beautySalon.dto.UserHeaderDto;
import com.beautySalon.service.CustomerService;
import com.beautySalon.service.UserService;
import com.beautySalon.validator.CustomerValidator;
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
public class CustomerController {

    // == fields ==
    private final CustomerService customerService;
    private final UserService userService;
    private final CustomerValidator customerValidator;
    private static final String CUSTOMER_DTO = "customerDto";
    private static final String USER_HEADER_DTO = "userHeaderDto";

    // == constructor ==
    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.userService = userService;
        this.customerValidator = customerValidator;
    }

    // == mapping methods ==
    @GetMapping("/customers")
    public String getAllCustomers(Model model, Authentication authentication) {
        log.info("getAllCustomers called");

        model.addAttribute("customers", customerService.getAllCustomers());
        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        return "customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerDetails(@PathVariable Long id, Model model, Authentication authentication) {
        log.info("getCustomerDetails called for customer ID: {}", id);

        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            return "redirect:/customers";
        }

        model.addAttribute(CUSTOMER_DTO, customerDto);
        UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
        model.addAttribute(USER_HEADER_DTO, userHeaderDto);

        return "customerDetails";
    }

    @PostMapping("/customer/{id}")
    public String updateCustomer(@PathVariable Long id, CustomerDto customerDto, BindingResult bindingResult,
                                  Authentication authentication, Model model) {
        log.info("updateCustomer called for customer ID: {}", id);

        customerValidator.validate(customerDto, bindingResult);

        if (bindingResult.hasErrors()) {
            UserHeaderDto userHeaderDto = userService.getUserHeaderDto(authentication.getName());
            model.addAttribute(USER_HEADER_DTO, userHeaderDto);

            model.addAttribute(CUSTOMER_DTO, customerDto);
            return "customerDetails";
        }

        customerService.updateCustomer(id, customerDto);
        return "redirect:/customers";
    }

    @PostMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        log.info("deleteCustomer called for customer ID: {}", id);
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
