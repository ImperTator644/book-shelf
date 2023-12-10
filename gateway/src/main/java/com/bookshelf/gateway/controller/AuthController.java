package com.bookshelf.gateway.controller;

import com.bookshelf.gateway.config.CurrentUser;
import com.bookshelf.gateway.model.CustomUserDetails;
import com.bookshelf.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

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
    public RedirectView addNewUser(@Valid CustomUserDetails userDetails) {
        var response = authService.saveUser(userDetails);
        if(response.getStatusCodeValue() == 200) {
            return new RedirectView("/");
        }
        return new RedirectView("/register?error="
                + Optional.ofNullable(response.getBody())
                .orElse(EMPTY)
                .replaceAll(SPACE, "+"));
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
