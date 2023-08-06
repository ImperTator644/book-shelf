package com.luximed.frontservice.controller;

import com.luximed.frontservice.model.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthUserController {
    private final CurrentUser currentUser;

    public AuthUserController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @PostMapping("auth/log")
    public void logUser(@RequestBody String pesel) {
        log.info("Received user with pesel {}", pesel);
        currentUser.setUserName(pesel);
    }
}
