package com.luximed.frontservice.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Component
public class AppointmentDto {
    private Integer id;
    private PatientDto patient;
    private DoctorDto doctor;
    private ClinicDto clinic;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime start, end;
    private String title;
    private AppointmentTypeDto appointmentType;
}
