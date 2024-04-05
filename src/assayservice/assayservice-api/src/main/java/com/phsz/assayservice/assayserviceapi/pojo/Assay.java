package com.phsz.assayservice.assayserviceapi.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Assay {
    private Long id;
    private String name;
    private String type;  // 假设化验有类型字段，与疫苗的制造商字段对应
    private Date date;  // 可能对应化验的日期，而不是过期日期
    private String description;  // 化验描述
    private BigDecimal price;

}