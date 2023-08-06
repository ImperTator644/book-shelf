package com.luximed.frontservice.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppointmentTypeDto {
    private Integer id;
    private SpecjalizationDto specjalization;
    private String name;
    private String description;
    private Integer duration;
}
