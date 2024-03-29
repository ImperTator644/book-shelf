package com.bookshelf.frontservice.controller;

import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;

import com.bookshelf.frontservice.client.DBClient;
import com.bookshelf.frontservice.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    private final CurrentUserService currentUserService;
    private final DBClient dbClient;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("profile")
    public String getProfilePage(@RequestParam(required = false) String message, ModelMap modelMap) {
        var currentUserName = currentUserService.getCurrentUser();
        if (currentUserName.equals(currentUserService.getEmptyUser())) {
            return "redirect:http://localhost:8080/user-login?error=Log in to see your profile";
        }
        var currentUser = dbClient.getUserByUsername(currentUserName);
        modelMap.put("user", currentUser);
        modelMap.put("message", message);
        return "user/user-profile";
    }

    @GetMapping("profile/edit")
    public String getEditProfilePage(
            @RequestParam(name = "error", required = false) String errorMessage, ModelMap modelMap) {
        var currentUserName = currentUserService.getCurrentUser();
        if (currentUserName.equals(currentUserService.getEmptyUser())) {
            return "redirect:http://localhost:8080/user-login?error=Log in to edit your profile";
        }
        var currentUser = dbClient.getUserByUsername(currentUserName);
        modelMap.put("user", currentUser);
        modelMap.put("errorMessage", errorMessage);
        return "user/user-profile-edit";
    }

    @PostMapping("profile/edit/name")
    public String editUserName(@RequestParam String username) {
        var currentUserName = currentUserService.getCurrentUser();
        if (currentUserName.equals(currentUserService.getEmptyUser())) {
            return "redirect:http://localhost:8080/user-login?error=Log in to edit your profile";
        }
        var response = dbClient.updateUsername(username, currentUserName);
        if (response.getStatusCode() == PARTIAL_CONTENT) {
            return String.format("redirect:http://localhost:8080/profile?message=%s", response.getBody());
        }
        currentUserService.setLoggedUserName(username);
        return String.format("redirect:http://localhost:8080/profile?message=%s", response.getBody());
    }

    @PostMapping("profile/edit/password")
    public String editPassword(
            @RequestParam("new-password") String newPassword,
            @RequestParam("old-password") String oldPassword,
            @RequestParam("repeat-password") String repeatPassword) {
        var currentUserName = currentUserService.getCurrentUser();
        if (currentUserName.equals(currentUserService.getEmptyUser())) {
            return "redirect:http://localhost:8080/user-login?error=Log in to edit your profile";
        }
        if (!repeatPassword.equals(newPassword)) {
            return String.format(
                    "redirect:http://localhost:8080/profile/edit?error=%s",
                    "Your new and repeated passwords don' match");
        }
        var encodedNewPassword = passwordEncoder.encode(newPassword);
        var responseBody = dbClient.updatePassword(encodedNewPassword, oldPassword, currentUserName)
                .getBody();
        return String.format("redirect:http://localhost:8080/profile?message=%s", responseBody);
    }
}
