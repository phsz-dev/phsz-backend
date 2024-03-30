package com.phsz.medicineservice.medicineserviceapi.pojo;

import lombok.Data;

import java.util.Date;

@Data

public class Medicine {
    private Long VaccineId;
    private String name;
    private String manufacturer;
    private Date expiryDate;


}
