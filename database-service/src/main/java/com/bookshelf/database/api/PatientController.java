package com.bookshelf.database.api;

import com.bookshelf.database.model.Patient;
import com.bookshelf.database.model.PersonalData;
import com.bookshelf.database.repository.PatientRepository;
import com.bookshelf.database.repository.PersonalDataRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Tag(name = "patient", description = "Patient API")
@RestController
@RequestMapping(value = "api/database/patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PersonalDataRepository personalDataRepository;

    public PatientController(PatientRepository patientRepository, PersonalDataRepository personalDataRepository) {
        this.patientRepository = patientRepository;
        this.personalDataRepository = personalDataRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Patient getPatientById(@PathVariable Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addPatient(@RequestBody String pesel) {
        PersonalData personalData = personalDataRepository.findPersonalDataByPesel(pesel);

        Patient patient = Patient.builder().personalData(personalData).build();
        patientRepository.save(patient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deletePatient(@RequestParam Integer id) {
        patientRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pesel/{pesel}")
    public Patient getPatientByPesel(@PathVariable String pesel) {
        return patientRepository.getPatientByPersonalDataPesel(pesel);
    }
}
