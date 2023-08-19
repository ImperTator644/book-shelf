package com.bookshelf.frontservice.client;

import com.bookshelf.frontservice.dto.ClinicDto;
import com.bookshelf.frontservice.dto.PatientDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "database-service")
public interface ClinicClient {
    @GetMapping(value = "api/database/clinic/{id}")
    ClinicDto getClinicById(@PathVariable("id") Integer id);

    @PostMapping(value = "/api/database/clinic/add")
    void addClinic(
            @RequestParam String buildingNumber,
            @RequestParam String city,
            @RequestParam String postalCode,
            @RequestParam String street);

    @PostMapping(value = "/api/database/delete-clinic")
    void deleteClinic(@RequestParam Integer id);

    @GetMapping(value = "/api/database/patient/all")
    List<PatientDto> getPatients();
}
