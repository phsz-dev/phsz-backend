package com.phsz.chargeservice.chargeserviceprovider.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.phsz.common.JsonbConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Charge", schema = "public")
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = JsonbConverter.class)
    private JsonNode details; // 假设这是新增的字段，表示收费详情
    private Date date; // 假设这是收费日期

    // 根据需要添加或修改字段
}