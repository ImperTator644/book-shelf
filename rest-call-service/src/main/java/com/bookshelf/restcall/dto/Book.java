package com.bookshelf.restcall.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Book {
    private String title;
    private String genre;
    private String author;
}
