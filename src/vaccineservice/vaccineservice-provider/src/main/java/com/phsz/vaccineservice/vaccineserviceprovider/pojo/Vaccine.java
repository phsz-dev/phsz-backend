package com.phsz.vaccineservice.vaccineserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Vaccine", schema = "public")
public class Vaccine {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private Date expiryDate;


}