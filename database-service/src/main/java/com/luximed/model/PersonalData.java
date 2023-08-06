package com.luximed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Builder
@AllArgsConstructor
public class PersonalData {

    @Id
    @Column(unique = true, nullable = false)
    private String pesel;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Email
    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public PersonalData() {

    }
}
