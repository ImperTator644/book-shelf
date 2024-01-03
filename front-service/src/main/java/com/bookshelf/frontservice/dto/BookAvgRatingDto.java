package com.bookshelf.frontservice.dto;

import lombok.Data;

@Data
public class BookAvgRatingDto {
    private BookFromDB book;
    private double avgRating;
}
