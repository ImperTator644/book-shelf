package com.luximed.api;

import com.luximed.model.Clinic;
import com.luximed.repository.ClinicRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "clinic", description = "Clinic API")
@RestController
@RequestMapping(value = "api/database/clinic")
public class ClinicController {

    private final ClinicRepository clinicRepository;

    public ClinicController(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Clinic getClinicById(@PathVariable Integer id) {
        return clinicRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/by-city/{city}")
    public List<Clinic> getClinicsByCity(@PathVariable String city) {
        return clinicRepository.getClinicsByCity(city);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addClinic(@RequestParam String buildingNumber,
                          @RequestParam String city,
                          @RequestParam String postalCode,
                          @RequestParam String street) {
        Clinic clinic = Clinic.builder()
                        .buildingNumber(buildingNumber)
                        .city(city)
                        .postalCode(postalCode)
                        .street(street)
                        .build();
        clinicRepository.save(clinic);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteClinic(@RequestParam Integer id) {
        clinicRepository.deleteById(id);
    }
}
