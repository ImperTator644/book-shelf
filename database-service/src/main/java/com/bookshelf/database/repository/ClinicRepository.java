package com.bookshelf.database.repository;

import com.bookshelf.database.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    List<Clinic> getClinicsByCity(String city);
}
