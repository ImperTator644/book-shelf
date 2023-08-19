package com.bookshelf.frontservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping(value = "/")
    public String getMainPage() {
        return "main-template";
    }
}
