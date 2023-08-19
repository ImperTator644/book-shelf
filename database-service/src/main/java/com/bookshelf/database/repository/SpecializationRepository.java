package com.bookshelf.database.repository;

import com.bookshelf.database.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    Specialization findFirstByName(String name);
}
