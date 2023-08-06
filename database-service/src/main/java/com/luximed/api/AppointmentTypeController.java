package com.luximed.api;

import com.luximed.exception.DatabaseException;
import com.luximed.model.AppointmentType;
import com.luximed.model.Specialization;
import com.luximed.repository.AppointmentTypeRepository;
import com.luximed.repository.SpecializationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "appointment type", description = "Appointment type API")
@RestController
@RequestMapping(value = "api/database/appointmentType")
public class AppointmentTypeController {

    private final AppointmentTypeRepository appointmentTypeRepository;
    private final SpecializationRepository specializationRepository;

    public AppointmentTypeController(AppointmentTypeRepository appointmentTypeRepository,
                                     SpecializationRepository specializationRepository) {
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.specializationRepository = specializationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<AppointmentType> getAllAppointmentTypes() {
        return appointmentTypeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AppointmentType getAppointmentTypeById(@PathVariable Integer id) {
        return appointmentTypeRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/spec/{specialization}")
    public List<AppointmentType> getAppointmentTypesBySpecId(@PathVariable String specialization) {
        return appointmentTypeRepository.getAppointmentTypesBySpecializationName(specialization);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addAppointmentType(@RequestParam String description,
                                   @RequestParam Integer duration,
                                   @RequestParam String name,
                                   @RequestParam Integer specializationId) {
        Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(() -> new DatabaseException());

        AppointmentType appointmentType = AppointmentType.builder()
                .description(description)
                .duration(duration)
                .name(name)
                .specialization(specialization)
                .build();
        appointmentTypeRepository.save(appointmentType);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteAppointmentType(@RequestParam Integer id) {
        appointmentTypeRepository.deleteById(id);
    }

}
