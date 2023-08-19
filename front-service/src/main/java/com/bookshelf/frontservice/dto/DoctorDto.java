package com.bookshelf.frontservice.dto;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DoctorDto {
    private Integer id;
    private PersonalDataDto personalData;
    private List<SpecjalizationDto> specjalization;
    private List<ClinicDto> clinics;
}
