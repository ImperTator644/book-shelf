package com.bookshelf.database.api;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

import com.bookshelf.database.model.User;
import com.bookshelf.database.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Tag(name = "user", description = "User API")
@RestController
@RequestMapping(value = "api/database/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @PostMapping("update/name")
    @Transactional
    public ResponseEntity<String> updateUsername(@RequestParam String newName, @RequestParam String oldname) {
        userRepository.updateUsername(newName, oldname);
        return ok("User updated successfully");
    }

    @PostMapping("update/password")
    @Transactional
    public ResponseEntity<String> updatePassword(@RequestParam String newPassword,
                                                 @RequestParam String oldPassword,
                                                 @RequestParam String username) {
        var userToUpdate = this.getUserByUsername(username);
        if (!passwordEncoder.matches(oldPassword, userToUpdate.getPassword())) {
            return ok("Provided old password is incorrect");
        }
        if (newPassword.length() < 5) {
            return ok("Provided password is to short (at least 5 characters needed)");
        }
        this.userRepository.updatePassword(newPassword, username);
        return ok("Password successfully changed");
    }
}
