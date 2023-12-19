package com.bookshelf.database.api;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

import com.bookshelf.database.model.UserBook;
import com.bookshelf.database.repository.BookRepository;
import com.bookshelf.database.repository.UserBookRepository;
import com.bookshelf.database.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "rating", description = "Rating API")
@RequestMapping(value = "api/database/rating")
@RequiredArgsConstructor
public class RatingController {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserBookRepository userBookRepository;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<String> addRating(
            @RequestParam String username, @RequestParam int bookID, @RequestParam int rating) {
        var user = userRepository.findByUsername(username);
        var book = bookRepository.findById(bookID).orElse(null);
        if (user == null || book == null) {
            return badRequest().body("No such user or book");
        }
        var ratingInDb = userBookRepository.findUserBookByUserAndBook(user, book);
        if (ratingInDb == null) {
            var userBookRecord =
                    UserBook.builder().book(book).user(user).rating(rating).build();
            userBookRepository.save(userBookRecord);
        } else {
            userBookRepository.updateRating(rating, ratingInDb.getId());
        }
        return ok("Successfully saved new rating");
    }

    @GetMapping
    public ResponseEntity<Integer> getUserRating(@RequestParam String username, @RequestParam int bookID) {
        var user = userRepository.findByUsername(username);
        var book = bookRepository.findById(bookID).orElse(null);
        if (user == null || book == null) {
            return badRequest().build();
        }
        var ratingInDb = userBookRepository.findUserBookByUserAndBook(user, book);
        if (ratingInDb == null) {
            return ok(0);
        }
        return ok(ratingInDb.getRating());
    }

    @GetMapping("/avg")
    public ResponseEntity<Double> getBookAvgRating(@RequestParam int bookID) {
        var book = bookRepository.findById(bookID).orElse(null);
        if (book == null) {
            return badRequest().build();
        }
        var ratingInDb = userBookRepository.findAllByBook(book);
        if (ratingInDb == null) {
            return badRequest().build();
        }
        if (ratingInDb.isEmpty()) {
            return ok(0d);
        }
        var avgRating = Math.round(ratingInDb.stream()
                                .map(UserBook::getRating)
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0D)
                        * 100.0)
                / 100.0;
        return ok(avgRating);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeRating(@RequestParam String username, @RequestParam int bookID) {
        var user = userRepository.findByUsername(username);
        var book = bookRepository.findById(bookID).orElse(null);
        if (user == null || book == null) {
            return badRequest().build();
        }
        var ratingInDb = userBookRepository.findUserBookByUserAndBook(user, book);
        if (ratingInDb == null) {
            return badRequest().build();
        }
        userBookRepository.delete(ratingInDb);
        return ok().build();
    }
}
