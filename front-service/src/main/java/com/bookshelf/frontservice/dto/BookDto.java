package com.bookshelf.frontservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class BookDto {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private int pageCount;
    private String language;
    private String smallThumbnail;
    private String thumbnail;
    private String description;
}
