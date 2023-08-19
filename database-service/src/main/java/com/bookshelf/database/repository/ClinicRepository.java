package com.bookshelf.database.repository;

import com.bookshelf.database.model.Clinic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    List<Clinic> getClinicsByCity(String city);
}
