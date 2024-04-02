package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CaseToDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "disease_id")
    private Long diseaseId;

}
