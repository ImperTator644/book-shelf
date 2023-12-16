package com.bookshelf.frontservice.client;

import com.bookshelf.frontservice.dto.BookDto;
import com.bookshelf.frontservice.dto.BookFromDB;
import com.bookshelf.frontservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "database-service")
public interface DBClient {
    @PostMapping(value = "api/database/book/add")
    ResponseEntity<Integer> addBook(@RequestBody BookDto book);

    @GetMapping("api/database/book/{id}")
    BookFromDB getBookByID(@PathVariable int id);

    @GetMapping("api/database/user/{username}")
    UserDTO getUserByUsername(@PathVariable String username);

    @PostMapping("api/database/user/update/name")
    void updateUsername(@RequestParam String newName, @RequestParam String oldname);

    @PostMapping("api/database/user/update/password")
    ResponseEntity<String> updatePassword(
            @RequestParam String newPassword, @RequestParam String oldPassword, @RequestParam String username);
}
