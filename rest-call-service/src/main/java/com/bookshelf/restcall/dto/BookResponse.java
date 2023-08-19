package com.bookshelf.restcall.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookResponse {
    private String nextPage;
    private Book[] bibs;
}
