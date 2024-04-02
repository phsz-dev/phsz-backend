package com.phsz.vaccineservice.vaccineserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "Vaccine",schema = "public")
public class Vaccine {
    @Id
    @Column(name = "vaccine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccineId;
    private String vaccineName;
    private String manufacturer;
    private Date expiryDate;


}