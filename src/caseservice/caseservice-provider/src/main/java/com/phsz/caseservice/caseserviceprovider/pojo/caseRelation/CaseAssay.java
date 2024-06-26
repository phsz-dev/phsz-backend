package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CaseAssay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long caseId;
    @Column
    private Long assayId;
    @Column
    private String result;
}
