package com.phsz.medicineservice.medicineserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Medicine {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type; // 新增属性type，代表药品类型

    @Column
    private String batchNumber; // 新增属性batchNumber，代表批次号

    @Column
    private Date validity; // 将expiryDate修改为validity，表示有效期

    @Column
    private String usage; // 新增属性usage，表示药品用途

    @Column
    private BigDecimal price; // 新增属性price，表示药品价格
}