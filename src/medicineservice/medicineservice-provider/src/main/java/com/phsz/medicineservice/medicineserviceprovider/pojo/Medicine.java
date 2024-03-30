package com.phsz.medicineservice.medicineserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "Medicine",schema = "public")
public class Medicine {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MedicineId;
    private String name;
    private String manufacturer;
    private Date expiryDate;


}