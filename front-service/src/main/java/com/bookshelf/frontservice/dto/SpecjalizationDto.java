package com.bookshelf.frontservice.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SpecjalizationDto {
    private Integer id;
    private String name;
}
