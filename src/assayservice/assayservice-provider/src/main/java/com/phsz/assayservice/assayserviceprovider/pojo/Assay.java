package com.phsz.assayservice.assayserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "assay", schema = "public")
public class Assay {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private Date date;
    @Column
    private BigDecimal price;
}