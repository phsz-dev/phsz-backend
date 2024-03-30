package com.phsz.chargeservice.chargeserviceapi.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Charge {
    private Long chargeId; // 将 VaccineId 改为 chargeId
    private String name;   // 名称字段可能保持不变
    private Double amount; // 假设替换制造商字段为费用金额
    private Date billingDate; // 将 expiryDate 改为 billingDate

}