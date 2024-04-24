package com.phsz.caseservice.caseserviceprovider.pojo;

import com.phsz.caseservice.caseserviceprovider.pojo.Medicine;
import lombok.Data;

import java.util.Date;

@Data
public class MedicineInfo {
    private Long id;
    private String name;
    private String usage;
    private Date validity;
    private String medicineDosage;

    public MedicineInfo MedicineInfoCons(Medicine medicine, String medicineDosage) {
        this.setId(medicine.getId());
        this.setName(medicine.getName());
        this.setMedicineDosage(medicineDosage);
        this.setUsage(medicine.getUsage());
        this.setValidity(medicine.getValidity());
        return this;
    }

}
