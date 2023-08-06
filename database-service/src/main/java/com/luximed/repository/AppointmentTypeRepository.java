package com.luximed.repository;

import com.luximed.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer> {
    List<AppointmentType> getAppointmentTypesBySpecializationName(String specialization);
}
