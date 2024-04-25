package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AssayInfo {
    private Long id;
    private String name;
    private Date date;
    private BigDecimal price;
    private String result;
}
