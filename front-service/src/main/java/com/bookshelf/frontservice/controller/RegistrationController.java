package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RegistrationController {
    @RequestMapping("/register")
    public String getRegisterUserPage(
            @RequestParam(name = "error", required = false) String errorMessage, Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("errorMessage", errorMessage);
        return "user/register";
    }

    @GetMapping("/user-login")
    public String loginUser() {
        return "user/login";
    }

    @GetMapping("/user-logout")
    public String logoutUser() {
        return "user/logout";
    }
}
