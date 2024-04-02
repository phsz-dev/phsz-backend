package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disease_type" ,schema = "public")
public class DiseaseType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}