package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.DBClient;
import com.bookshelf.frontservice.dto.BookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final ObjectMapper objectMapper;
    private final DBClient dbClient;

    @GetMapping
    public String redirectToMainPage() {
        return "redirect:http://localhost:8080/";
    }

    @GetMapping("/{bookID}")
    public String getTestBook(@PathVariable int bookID, ModelMap modelMap) {
        var bookFromDB = dbClient.getBookByID(bookID);
        modelMap.put("book", bookFromDB);
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
        var bookId = dbClient.addBook(book).getBody();
        var url = String.format("http://localhost:8080/book/%d", bookId);
        return "redirect:" + url;
    }
}
