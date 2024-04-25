package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VaccineInfo {
    private Long id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private Date expiryDate;
}
