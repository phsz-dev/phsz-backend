package com.phsz.hospitalizationservice.hospitalizationserviceprovider.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "hospitalization",schema = "public")
public class Hospitalization {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalizationId;

    private Long petId;
    private Date admissionDate;
    private Date dischargeDate;
    private String diagnosis;
    private String treatment;
    private Long doctorId;
    private Long wardId;
}
