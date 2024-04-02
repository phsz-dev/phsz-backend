package com.phsz.chargeservice.chargeserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Charge", schema = "public")
public class Charge {
    @Id
    @Column(name = "charge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chargeId;
    private String name; // 你可能需要根据收费记录的实际需求调整字段
    private String details; // 假设这是新增的字段，表示收费详情
    private Date date; // 假设这是收费日期

    // 根据需要添加或修改字段
}