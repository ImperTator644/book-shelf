package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.DBClient;
import com.bookshelf.frontservice.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final CurrentUserService currentUserService;
    private final DBClient dbClient;

    @GetMapping(value = "/")
    public String getMainPage(ModelMap modelMap) {
        if(!currentUserService.getCurrentUser().equals(currentUserService.getEmptyUser())) {
            modelMap.put("currentUser", currentUserService.getCurrentUser());
        }
        var topRatings = dbClient.getTopRatedBooks();
        if(topRatings.size() > 3) {
            modelMap.put("topRatings", topRatings);
        }
        return "main-template";
    }
}
