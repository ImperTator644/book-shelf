package com.bookshelf.database.repository;

import com.bookshelf.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Modifying
    @Query("UPDATE User u set u.username = :newName where u.username LIKE :oldName")
    void updateUsername(@Param("newName") String newName, @Param("oldName") String oldName);

    @Modifying
    @Query("UPDATE User u set u.password = :newPassword where u.username LIKE :username")
    void updatePassword(@Param("newPassword") String newPassword, @Param("username") String username);
}
