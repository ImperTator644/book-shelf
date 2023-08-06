package com.luximed.gateway.controller;

import com.luximed.gateway.model.CustomUserDetails;
import com.luximed.gateway.model.UserCredential;
import com.luximed.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.validation.Valid;

@Controller
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "auth/register")
    public RedirectView addNewPatient(@Valid CustomUserDetails userDetails) {
        authService.savePatient(userDetails);
        return new RedirectView("/");
    }

    @PostMapping(value = "login")
    @ResponseBody
    public String getToken(UserCredential userCredential) {
        return authService.generateToken(userCredential.getUsername());
    }

    @GetMapping(value = "validate")
    @ResponseBody
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
