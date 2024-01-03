package com.bookshelf.frontservice.client;

import com.bookshelf.frontservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "database-service")
public interface DBClient {
    @PostMapping(value = "api/database/book/add")
    ResponseEntity<Integer> addBook(@RequestBody BookDto book);

    @GetMapping("api/database/book/{id}")
    BookFromDB getBookByID(@PathVariable int id);

    @GetMapping("api/database/user/{username}")
    UserDTO getUserByUsername(@PathVariable String username);

    @PostMapping("api/database/user/update/name")
    ResponseEntity<String> updateUsername(@RequestParam String newName, @RequestParam String oldname);

    @PostMapping("api/database/user/update/password")
    ResponseEntity<String> updatePassword(
            @RequestParam String newPassword, @RequestParam String oldPassword, @RequestParam String username);

    @PostMapping("api/database/rating/add")
    ResponseEntity<String> addRating(@RequestParam String username, @RequestParam int bookID, @RequestParam int rating);

    @GetMapping("api/database/rating")
    ResponseEntity<Integer> getUserRating(@RequestParam String username, @RequestParam int bookID);

    @DeleteMapping("api/database/rating/delete")
    ResponseEntity<Void> removeRating(@RequestParam String username, @RequestParam int bookID);

    @GetMapping("api/database/rating/avg")
    ResponseEntity<Double> getBookAvgRating(@RequestParam int bookID);

    @GetMapping("api/database/book/users/all")
    List<UserBookDto> getAllUsersBooks(@RequestParam String username);

    @GetMapping("api/database/book/top-rated")
    List<BookAvgRatingDto> getTopRatedBooks();
}
