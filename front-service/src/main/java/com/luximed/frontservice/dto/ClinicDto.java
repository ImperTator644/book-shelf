package com.luximed.frontservice.dto;

import lombok.Data;

@Data
public class ClinicDto {
    private Integer id;
    private String city;
    private String street;
    private String buildingNumber;
    private String postalCode;
}
