package com.bookshelf.database.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    private String title;

    private String authors;

    private String publishedDate;

    private int pageCount;

    private String language;

    private String smallThumbnail;

    private String thumbnail;

    private String description;
}
