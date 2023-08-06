package com.luximed.frontservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PersonalDataDto {
    @JsonProperty("pesel")
    private String username;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String password;
    private Gender gender;
}
