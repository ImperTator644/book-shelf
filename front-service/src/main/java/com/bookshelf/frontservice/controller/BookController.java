package com.bookshelf.frontservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @GetMapping
    public String getBookOverviewPage() {
        return "books/book-overview";
    }
}
