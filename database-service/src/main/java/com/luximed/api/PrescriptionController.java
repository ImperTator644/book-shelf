package com.luximed.api;

import com.luximed.exception.DatabaseException;
import com.luximed.model.Appointment;
import com.luximed.model.Prescription;
import com.luximed.repository.AppointmentRepository;
import com.luximed.repository.PrescriptionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "prescription", description = "Prescription API")
@RestController
@RequestMapping(value = "api/database/prescription")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository,
                                  AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Prescription getPrescriptionById(@PathVariable Integer id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addPrescription(@RequestParam String description,
                          @RequestParam LocalDate expirationDate,
                          @RequestParam Integer appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new DatabaseException());

        Prescription prescription = Prescription.builder()
                .description(description)
                .expirationDate(expirationDate)
                .appointment(appointment)
                .build();
        prescriptionRepository.save(prescription);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deletePrescription(@RequestParam Integer id) {
        prescriptionRepository.deleteById(id);
    }

}
