package com.luximed.frontservice.controller;

import com.luximed.frontservice.client.ReservationClient;
import com.luximed.frontservice.dto.AppointmentTypeDto;
import com.luximed.frontservice.dto.ClinicDto;
import com.luximed.frontservice.dto.DoctorDto;
import com.luximed.frontservice.model.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationClient reservationClient;
    private final CurrentUser currentUser;

    public ReservationController(ReservationClient reservationClient, CurrentUser currentUser) {
        this.reservationClient = reservationClient;
        this.currentUser = currentUser;
    }

    @GetMapping("find-doctors")
    public String findDoctorsPage() {
        return "find-doctors";
    }

    @GetMapping("find-doctors/by")
    public String findDoctorsBy(@RequestParam(defaultValue = "") String specialization,
                                @RequestParam(defaultValue = "") String city,
                                ModelMap model) {

        List<DoctorDto> foundDoctors;
        List<AppointmentTypeDto> appointmentTypeDtos;
        if (!specialization.isBlank() && !city.isBlank()) {
            foundDoctors = reservationClient.getDoctorsBySpecializationAndClinic(specialization, city);
            model.put("doctors", foundDoctors);
            appointmentTypeDtos = reservationClient.getAppointmentTypesBySpec(specialization);
            model.put("appointmentTypes", appointmentTypeDtos);
            LocalDate now = LocalDate.now();
            model.put("now", now);
        }
        return "find-doctors";
    }

    @PostMapping("appointment/add")
    public String makeAnAppointment(@RequestParam String date,
                                    @RequestParam String dateTime,
                                    @RequestParam Integer clinicId,
                                    @RequestParam Integer doctorId,
                                    @RequestParam Integer appointmentTypeId,
                                    ModelMap model) {

        Integer patientId = reservationClient.getPatientByPesel(currentUser.getUserName()).getId();
        reservationClient.addAppointment(patientId, date, dateTime, clinicId, doctorId, appointmentTypeId);
        DoctorDto chosenDoctor = reservationClient.getDoctorById(doctorId);
        ClinicDto chosenClinic = reservationClient.getClinicById(clinicId);
        AppointmentTypeDto chosenAppointmentType = reservationClient.getAppointmentTypeById(appointmentTypeId);

        model.put("doctor", chosenDoctor);
        model.put("date", date);
        model.put("dateTime", dateTime);
        model.put("appointmentType", chosenAppointmentType);
        model.put("clinic", chosenClinic);

        return "created-appointment";
    }

}