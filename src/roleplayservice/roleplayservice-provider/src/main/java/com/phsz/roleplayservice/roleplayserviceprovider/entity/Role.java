package com.phsz.roleplayservice.roleplayserviceprovider.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "role",schema = "public")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;
    private String description;
}