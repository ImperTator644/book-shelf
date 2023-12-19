package com.bookshelf.database.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserBook {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    private Integer rating;
}
