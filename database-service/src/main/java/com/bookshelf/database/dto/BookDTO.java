package com.bookshelf.database.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private int pageCount;
    private String language;
    private String smallThumbnail;
    private String thumbnail;
    private String description;
}
