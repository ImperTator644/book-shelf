package com.bookshelf.restcall.dto;

import java.util.List;
import lombok.Data;

@Data
public class BooksResponse {
    private List<BookData> items;
}
