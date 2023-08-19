package com.bookshelf.gateway.controller;

import com.bookshelf.gateway.config.CurrentUser;
import com.bookshelf.gateway.model.CustomUserDetails;
import com.bookshelf.gateway.model.UserCredential;
import com.bookshelf.gateway.service.AuthService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.RedirectView;

@Controller
@Slf4j
public class AuthController {

    @Value("${log.current.empty.user}")
    private String emptyUser;

    private final AuthService authService;
    private final CurrentUser currentUser;

    public AuthController(AuthService authService, CurrentUser currentUser) {
        this.authService = authService;
        this.currentUser = currentUser;
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

    @GetMapping(value = "logged-user")
    @ResponseBody
    public String getLoggedUser(@RequestParam("token") String token) {
        if (authService.validateLoggedUserToken(token)) {
            return currentUser.getUserName();
        }
        return emptyUser;
    }
}
