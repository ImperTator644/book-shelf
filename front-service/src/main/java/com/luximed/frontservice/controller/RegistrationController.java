package com.luximed.frontservice.controller;

import com.luximed.frontservice.dto.PersonalDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RegistrationController {
    @RequestMapping("/register")
    public String getRegisterUserPage(@RequestParam(name = "error", required = false) String errorMessage, Model model){
        model.addAttribute("patient", new PersonalDataDto());
        model.addAttribute("errorMessage", errorMessage);
        return "registration/register-user";
    }
}
