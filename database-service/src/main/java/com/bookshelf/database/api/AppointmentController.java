package com.bookshelf.database.api;

import com.bookshelf.database.exception.DatabaseException;
import com.bookshelf.database.model.*;
import com.bookshelf.database.repository.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Tag(name = "appointment", description = "Appointment API")
@RestController
@RequestMapping(value = "api/database/appointment")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
                                 PatientRepository patientRepository,
                                 ClinicRepository clinicRepository,
                                 AppointmentTypeRepository appointmentTypeRepository,
                                 DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.clinicRepository = clinicRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Appointment getAppointment(@PathVariable Integer id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/patient/{pesel}")
    public List<Appointment> getAppointmentsByPesel(@PathVariable String pesel){
        return appointmentRepository.getAppointmentsByPesel(pesel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doctor/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Integer id){
        return appointmentRepository.getAppointmentsByDoctorId(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addAppointment(@RequestParam Integer patientId,
                               @RequestParam String date,
                               @RequestParam String dateTime,
                               @RequestParam Integer clinicId,
                               @RequestParam Integer doctorId,
                               @RequestParam Integer appointmentTypeId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(DatabaseException::new);
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(DatabaseException::new);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(DatabaseException::new);
        AppointmentType appointmentType = appointmentTypeRepository.findById(appointmentTypeId).orElseThrow(DatabaseException::new);

        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(dateTime);

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .date(localDate)
                .time(localTime)
                .clinic(clinic)
                .doctor(doctor)
                .appointmentType(appointmentType)
                .build();
        appointmentRepository.save(appointment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteAppointment(@RequestParam Integer id) {
        appointmentRepository.deleteById(id);
    }
}
