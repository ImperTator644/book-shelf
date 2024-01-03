package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.DBClient;
import com.bookshelf.frontservice.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final DBClient dbClient;
    private final CurrentUserService currentUserService;

    @GetMapping
    public String getAllUsersBooks(ModelMap modelMap) {
        var usersBooks = dbClient.getAllUsersBooks(currentUserService.getCurrentUser());
        modelMap.put("usersBooks", usersBooks);
        return "books/user-catalog";
    }
}
