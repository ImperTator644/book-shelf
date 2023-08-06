package com.luximed.api;

import com.luximed.exception.DatabaseException;
import com.luximed.model.*;
import com.luximed.repository.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Tag(name = "doctor", description = "Doctor API")
@RestController
@RequestMapping(value = "api/database/doctor")
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final PersonalDataRepository personalDataRepository;
    private final SpecializationRepository specializationRepository;
    private final ClinicRepository clinicRepository;

    public DoctorController(DoctorRepository doctorRepository,
                            PersonalDataRepository personalDataRepository,
                            SpecializationRepository specializationRepository,
                            ClinicRepository clinicRepository) {
        this.doctorRepository = doctorRepository;
        this.personalDataRepository = personalDataRepository;
        this.specializationRepository = specializationRepository;
        this.clinicRepository = clinicRepository;
    }


    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addDoctor(@RequestParam String pesel,
                          @RequestParam List<String> specializations) {
        PersonalData personalData = personalDataRepository.findPersonalDataByPesel(pesel);
        List<Specialization> specializationList = new ArrayList<>();

        specializations.forEach(s -> {
            Specialization specialization = specializationRepository.findFirstByName(s);
            if (nonNull(specialization)) {
                specializationList.add(specialization);
            }
        });

        Doctor doctor = Doctor.builder()
                .personalData(personalData)
                .specializations(specializationList)
                .build();
        doctorRepository.save(doctor);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Doctor getDoctorById(@PathVariable Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/by-spec/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String specialization) {
        return doctorRepository.getDoctorsBySpecialization(specialization);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/by-city/{city}")
    public List<Doctor> getDoctorsByCity(@PathVariable String city) {
        return doctorRepository.getDoctorsByCity(city);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add-clinic")
    public void addClinicToDoctor(@RequestParam Integer doctorId,
                          @RequestParam Integer clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElse(null);
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        if (isNull(clinic) || isNull(doctor)) {
            throw new DatabaseException("There is no clinic or doctor with such ID");
        }

        doctor.getClinics().add(clinic);
        doctorRepository.save(doctor);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add-specialization")
    public void addSpecializationToDoctor(@RequestParam Integer doctorId,
                                  @RequestParam Integer specializationId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        Specialization specialization = specializationRepository.findById(specializationId).orElse(null);

        if (isNull(specialization) || isNull(doctor)) {
            throw new DatabaseException("There is no specialization or doctor with such ID");
        }

        doctor.getSpecializations().add(specialization);
        doctorRepository.save(doctor);
    }

    @GetMapping(value = "by-city-and-spec")
    public List<Doctor> getDoctorsBySpecializationAndClinic(@RequestParam String specialization, @RequestParam String city) {
        return doctorRepository.getDoctorBySpecializationAndCity(specialization, city);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteDoctor(@RequestParam Integer id) {
        doctorRepository.deleteById(id);
    }


}
