package com.luximed.repository;

import com.luximed.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT DISTINCT doctor.id, doctor.personal_data_pesel FROM doctor\n" +
            "join doctor_specializations on doctor.id = doctor_specializations.doctor_id\n" +
            "join doctor_clinics on doctor.id = doctor_clinics.doctor_id\n" +
            "join clinic on doctor_clinics.clinics_id = clinic.id\n" +
            "join specialization on specialization.id = doctor_specializations.specializations_id\n" +
            "WHERE specialization.name LIKE ?1 AND clinic.city LIKE ?2", nativeQuery = true)
    List<Doctor> getDoctorBySpecializationAndCity(String specialization, String city);

    @Query(value = "SELECT DISTINCT doctor.id, doctor.personal_data_pesel FROM doctor\n" +
            "join doctor_specializations on doctor.id = doctor_specializations.doctor_id\n" +
            "join specialization on specialization.id = doctor_specializations.specializations_id\n" +
            "WHERE specialization.name LIKE :specialization", nativeQuery = true)
    List<Doctor> getDoctorsBySpecialization(String specialization);

    @Query(value = "SELECT DISTINCT doctor.id, doctor.personal_data_pesel FROM doctor\n" +
            "join doctor_clinics on doctor.id = doctor_clinics.doctor_id\n" +
            "join clinic on doctor_clinics.clinics_id = clinic.id\n" +
            "WHERE clinic.city LIKE :city", nativeQuery = true)
    List<Doctor> getDoctorsByCity(String city);
}
