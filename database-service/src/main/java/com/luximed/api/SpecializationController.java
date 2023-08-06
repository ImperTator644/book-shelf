package com.luximed.api;

import com.luximed.model.Specialization;
import com.luximed.repository.SpecializationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "specialization", description = "Specialization API")
@RestController
@RequestMapping(value = "api/database/specialization")
public class SpecializationController {

    private final SpecializationRepository specializationRepository;

    public SpecializationController(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Specialization getSpecializationById(@PathVariable Integer id) {
        return specializationRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addSpecialization(@RequestParam String name) {
        Specialization specialization = Specialization.builder()
                .name(name)
                .build();
        specializationRepository.save(specialization);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteSpecialization(@RequestParam Integer id) {
        specializationRepository.deleteById(id);
    }

}
