package com.phsz.officeservice.officeserviceapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "office",schema = "public")
public class Office {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String responsibility;
    private String serviceHours;
    private Long managerId;
}