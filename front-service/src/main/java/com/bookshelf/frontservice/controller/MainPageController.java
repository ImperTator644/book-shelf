package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final CurrentUserService currentUserService;

    @GetMapping(value = "/")
    public String getMainPage(ModelMap modelMap) {
        if(currentUserService.getCurrentUser().equals(currentUserService.getEmptyUser())) {
            return "main-template";
        }
        modelMap.put("currentUser", currentUserService.getCurrentUser());
        return "main-template";
    }
}
