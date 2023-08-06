package com.luximed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(nullable = false)
    private PersonalData personalData;

    public Patient() {

    }
}
