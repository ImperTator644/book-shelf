package com.bookshelf.restcall.dto;

import lombok.Data;

@Data
public class VolumeInfo {
    private String title;
    private String[] authors;
    private String publishedDate;
    private int pageCount;
    private String language;
    private ImageLinks imageLinks;
    private String smallThumbnail;
    private String thumbnail;
    private String description;
}
