package com.luximed.repository;

import com.luximed.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    List<Clinic> getClinicsByCity(String city);
}
