package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_case", schema = "public")
public class Case {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column
    private String brief;
    @Column(name = "submit_time")
    private Long submitTime;
    @Column(name = "charge_id")
    private Long chargeId;
    @Column(name = "doctor_name")
    private String doctorName;

    public Case CaseCons(CaseInfo caseInfo) {
        this.name = caseInfo.getName();
        this.description = caseInfo.getDescription();
        this.submitTime = caseInfo.getSubmitTime();
        this.doctorName = caseInfo.getDoctorName();
        this.brief = caseInfo.getBrief();
        return this;
    }
}
