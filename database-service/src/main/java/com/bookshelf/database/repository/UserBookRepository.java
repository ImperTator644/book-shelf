package com.bookshelf.database.repository;

import com.bookshelf.database.model.Book;
import com.bookshelf.database.model.User;
import com.bookshelf.database.model.UserBook;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserBookRepository extends JpaRepository<UserBook, UUID> {
    UserBook findUserBookByUserAndBook(User user, Book book);

    List<UserBook> findAllByBook(Book book);

    List<UserBook> findAllByUser(User user);

    @Modifying
    @Query("UPDATE UserBook u set u.rating = :newRating where u.id = :userBookID")
    void updateRating(@Param("newRating") int newRating, @Param("userBookID") UUID userBookID);

    @Query(value = "SELECT avg(u.rating) as rating FROM UserBook u GROUP BY u.book ORDER BY rating DESC")
    List<Double> findTop4RatingNumber();

    @Query(value = "SELECT u.book FROM UserBook u GROUP BY u.book ORDER BY avg(u.rating) DESC")
    List<Book> findTop4RatingBooks();
}
