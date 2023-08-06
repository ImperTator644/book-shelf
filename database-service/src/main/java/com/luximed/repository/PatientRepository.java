package com.luximed.repository;

import com.luximed.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient getPatientByPersonalDataPesel(String pesel);
}
