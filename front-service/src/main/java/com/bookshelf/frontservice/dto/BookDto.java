package com.bookshelf.frontservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<String> authors;
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

    public String asJson(){
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "ERROR";
        }
    }
}
