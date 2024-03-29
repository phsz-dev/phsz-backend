package com.phsz.officeservice.officeserviceprovider.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "office",schema = "public")
public class Office {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId;

    private String officeName;
    private String location;
    private String responsibility;
    private String serviceHours;
    private Long managerId;
}