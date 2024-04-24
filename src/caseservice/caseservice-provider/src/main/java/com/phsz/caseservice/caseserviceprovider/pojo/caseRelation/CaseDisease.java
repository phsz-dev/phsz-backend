package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CaseDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long caseId;

    @Column
    private Long diseaseId;

}
