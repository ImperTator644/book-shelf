package com.bookshelf.database.api;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

import com.bookshelf.database.model.User;
import com.bookshelf.database.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "User API")
@RestController
@RequestMapping(value = "api/database/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            return ok("User saved to DB");
        }
        return badRequest().body("Username already taken");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteUser(@RequestParam String username) {
        userRepository.delete(this.getUserByUsername(username));
    }
}
