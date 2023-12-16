package com.bookshelf.database.api;

import com.bookshelf.database.dto.BookDTO;
import com.bookshelf.database.model.Book;
import com.bookshelf.database.repository.BookRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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
    public ResponseEntity<Integer> addBook(@RequestBody BookDTO bookDTO) {
        String authors;
        if (bookDTO.getAuthors() == null) {
            authors = null;
        } else if (bookDTO.getAuthors().size() == 1) {
            authors = bookDTO.getAuthors().get(0);
        } else {
            authors = String.join(", ", bookDTO.getAuthors());
        }
        var book = Book.builder()
                .authors(authors)
                .title(bookDTO.getTitle())
                .pageCount(bookDTO.getPageCount())
                .language(bookDTO.getLanguage())
                .thumbnail(bookDTO.getThumbnail())
                .smallThumbnail(bookDTO.getSmallThumbnail())
                .description(bookDTO.getDescription())
                .publishedDate(bookDTO.getPublishedDate())
                .build();
        var bookInDb = this.getBookByTitleAndAuthors(book.getTitle(), book.getAuthors());
        if (bookInDb != null) {
            return ok().body(bookInDb.getId());
        }
        bookRepository.save(book);
        bookInDb = this.getBookByTitleAndAuthors(book.getTitle(), book.getAuthors());
        return ok(bookInDb.getId());
    }
}