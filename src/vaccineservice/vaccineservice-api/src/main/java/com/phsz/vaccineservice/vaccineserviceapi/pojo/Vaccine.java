package com.phsz.vaccineservice.vaccineserviceapi.pojo;

import lombok.Data;

import java.util.Date;

@Data

public class Vaccine {
    private Long VaccineId;
    private String name;
    private String manufacturer;
    private Date expiryDate;


}