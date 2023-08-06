package com.luximed.repository;

import com.luximed.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    Specialization findFirstByName(String name);
}
