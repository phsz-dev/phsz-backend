package com.phsz.medicineservice.medicineserviceapi.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Medicine {
    private Long medicineId; // 将MedicineId修改为medicineId，符合Java命名规范
    private String medicineName; // 将name修改为medicineName
    private String type; // 新增属性type，代表药品类型
    private String batchNumber; // 新增属性batchNumber，代表批次号
    private Date validity; // 将expiryDate修改为validity，表示有效期
    private String usage; // 新增属性usage，表示药品用途
    private Double price; // 新增属性price，表示药品价格
}
