package com.bookshelf.database.api;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

import com.bookshelf.database.model.Book;
import com.bookshelf.database.repository.BookRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "book", description = "Book API")
@RequestMapping(value = "api/database/book")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookByID(@PathVariable int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @GetMapping("/title-authors")
    public Book getBookByTitleAndAuthors(@RequestParam String title, @RequestParam String authors) {
        return bookRepository.getBookByTitleAndAuthors(title, authors);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (this.getBookByTitleAndAuthors(book.getTitle(), book.getAuthors()) != null) {
            return badRequest().body("Book already in DB");
        }
        bookRepository.save(book);
        return ok("Book successfully saved to DB");
    }
}
