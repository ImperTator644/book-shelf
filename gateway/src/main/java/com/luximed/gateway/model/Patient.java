package com.luximed.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@AllArgsConstructor
public class Patient {
    @Id
    private Integer id;
    @Column("personal_data_pesel")
    private String personalData;
}
