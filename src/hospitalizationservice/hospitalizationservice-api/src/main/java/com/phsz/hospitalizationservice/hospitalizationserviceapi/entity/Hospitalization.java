package com.phsz.hospitalizationservice.hospitalizationserviceapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Hospitalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long petId;
    private Date admissionDate;
    private Date dischargeDate;
    private String diagnosis;
    private String treatment;
    private Long doctorId;
    private Long wardId;
}
