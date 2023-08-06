package com.luximed.frontservice.client;

import com.luximed.frontservice.dto.AppointmentTypeDto;
import com.luximed.frontservice.dto.ClinicDto;
import com.luximed.frontservice.dto.DoctorDto;
import com.luximed.frontservice.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "reservation-service")
public interface ReservationClient {

    @GetMapping(value = "/api/reservation/doctors/by-city-and-spec")
    List<DoctorDto> getDoctorsBySpecializationAndClinic(@RequestParam String specialization, @RequestParam String city);

    @GetMapping(value = "/api/reservation/patient/pesel/{pesel}")
    PatientDto getPatientByPesel(@PathVariable String pesel);

    @PostMapping(value = "/api/appointment/add")
    void addAppointment(@RequestParam Integer patientId,
                               @RequestParam String date,
                               @RequestParam String dateTime,
                               @RequestParam Integer clinicId,
                               @RequestParam Integer doctorId,
                               @RequestParam Integer appointmentTypeId);

    @GetMapping(value = "/api/appointment/spec/{specialization}")
    List<AppointmentTypeDto> getAppointmentTypesBySpec(@PathVariable String specialization);

    @GetMapping(value = "/api/appointment/type/{id}")
    AppointmentTypeDto getAppointmentTypeById(@PathVariable Integer id);

    @GetMapping(value = "/api/reservation/doctor/{id}")
    DoctorDto getDoctorById(@PathVariable Integer id);

    @GetMapping(value = "/api/reservation/clinic/{id}")
    ClinicDto getClinicById(@PathVariable Integer id);
}
