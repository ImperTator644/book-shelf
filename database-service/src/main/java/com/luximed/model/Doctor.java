package com.luximed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(nullable = false)
    private PersonalData personalData;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Specialization> specializations;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Clinic> clinics;
    public Doctor() {

    }
}
