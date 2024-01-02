package com.bookshelf.frontservice.dto;

import lombok.Data;

@Data
public class UserBookDto {
    private BookFromDB book;
    private Integer rating;
}