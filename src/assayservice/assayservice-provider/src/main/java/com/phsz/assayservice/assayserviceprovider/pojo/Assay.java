package com.phsz.assayservice.assayserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "assay" ,schema = "public")
public class Assay {
    @Id
    @Column(name = "assay_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assayId;
    @Column(name = "assay_name")
    private String assayName;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Date price;




}