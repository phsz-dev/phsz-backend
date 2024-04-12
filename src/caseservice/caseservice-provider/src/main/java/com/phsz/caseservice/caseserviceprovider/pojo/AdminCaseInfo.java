package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.List;


public interface AdminCaseInfo {
    Long getId();
    String getName();
    String getDiseases();
    String getDescription();
    Long getSubmitTime();
    String getAssays();
    String getMedicines();
    String getVaccines();
    Long getChargeId();
    String getBrief();
    String getDoctorName();
}
