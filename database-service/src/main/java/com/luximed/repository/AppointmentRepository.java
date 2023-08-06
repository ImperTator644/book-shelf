package com.luximed.repository;

import com.luximed.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM Appointment a JOIN patient p on a.patient_id = p.id WHERE p.personal_data_pesel = ?1",nativeQuery = true)
    List<Appointment> getAppointmentsByPesel(String pesel);

    @Query(value = "SELECT * FROM Appointment a WHERE a.doctor_id = ?1",nativeQuery = true)
    List<Appointment> getAppointmentsByDoctorId(Integer id);

}
