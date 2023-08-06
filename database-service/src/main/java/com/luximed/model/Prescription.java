package com.luximed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Appointment appointment;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate expirationDate;

    public Prescription() {

    }
}
