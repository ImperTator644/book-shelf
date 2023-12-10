package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.dto.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
@Slf4j
public class BookController {

    private final ObjectMapper objectMapper;

    public BookController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String getTestBook(@RequestParam String title, @RequestParam String author) {
        //get book from DB
        return "books/book-overview";
    }

    @PostMapping
    public String getBook(@RequestParam String bookToDisplay) {
        BookDto book;
        try {
            book = objectMapper.readValue(bookToDisplay, BookDto.class);
        } catch (JsonProcessingException e) {
            return "redirect:http://localhost:8080/";
        }
        //save book to db if it's not already there
        var url = String.format("http://localhost:8080/book?title=%s&author=%s", book.getTitle(), book.getAuthors().get(0))
                .replaceAll("\\[", "%5B")
                .replaceAll("]", "%5D");
        return "redirect:" + url;
    }
}
