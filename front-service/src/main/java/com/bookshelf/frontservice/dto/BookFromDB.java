package com.bookshelf.frontservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookFromDB {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private String authors;

    @JsonProperty("publishedDate")
    private String publishedDate;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("language")
    private String language;

    @JsonProperty("smallThumbnail")
    private String smallThumbnail;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("description")
    private String description;
}
