package com.bookshelf.database.repository;

import com.bookshelf.database.model.AppointmentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer> {
    List<AppointmentType> getAppointmentTypesBySpecializationName(String specialization);
}
