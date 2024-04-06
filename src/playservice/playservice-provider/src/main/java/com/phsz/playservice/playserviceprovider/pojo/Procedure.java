package com.phsz.playservice.playserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnoreProperties(value = {"procedures"})
    @JoinColumn(name = "sub_responsibility_id")
    private SubResponsibility subResponsibility;

    @Column
    private String content;

    @Column
    private Integer rank;
}
