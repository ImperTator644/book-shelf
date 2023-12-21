package com.bookshelf.frontservice.controller;

import com.bookshelf.frontservice.client.DBClient;
import com.bookshelf.frontservice.dto.BookDto;
import com.bookshelf.frontservice.service.CurrentUserService;
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
    private final CurrentUserService currentUser;

    @GetMapping
    public String redirectToMainPage() {
        return "redirect:http://localhost:8080/";
    }

    @GetMapping("/{bookID}")
    public String getTestBook(@PathVariable int bookID, ModelMap modelMap) {
        var bookFromDB = dbClient.getBookByID(bookID);
        var avgRating = dbClient.getBookAvgRating(bookID).getBody();
        modelMap.put("book", bookFromDB);
        modelMap.put("avgRating", avgRating);
        if (currentUser.getCurrentUser().equals(currentUser.getEmptyUser())) {
            return "books/book-overview";
        }
        var userRating =
                dbClient.getUserRating(currentUser.getCurrentUser(), bookID).getBody();
        modelMap.put("currentRating", userRating);
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

    @PostMapping("/rating")
    public String rateBook(@RequestParam(defaultValue = "0") int rating, @RequestParam("book-id") int bookID) {
        log.info("Got rating = {} for bookID {}", rating, bookID);

        dbClient.addRating(currentUser.getCurrentUser(), bookID, rating);

        return String.format("redirect:http://localhost:8080/book/%d", bookID);
    }

    @PostMapping("/rating/remove")
    public String removeRating(@RequestParam("book-id") int bookID) {
        dbClient.removeRating(currentUser.getCurrentUser(), bookID);
        return String.format("redirect:http://localhost:8080/book/%d", bookID);
    }
}
