package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CaseMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long caseId;

    @Column
    private Long medicineId;

    @Column
    private String medicineDosage;
}
