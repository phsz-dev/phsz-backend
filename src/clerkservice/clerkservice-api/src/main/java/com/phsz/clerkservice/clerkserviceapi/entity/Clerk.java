package com.phsz.clerkservice.clerkserviceapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clerk",schema = "public")
public class Clerk {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String serviceHours;
    private Long officeId;
}
