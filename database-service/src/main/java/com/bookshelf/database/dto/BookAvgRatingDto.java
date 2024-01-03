package com.bookshelf.database.dto;

import com.bookshelf.database.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookAvgRatingDto {
    private Book book;
    private double avgRating;
}
