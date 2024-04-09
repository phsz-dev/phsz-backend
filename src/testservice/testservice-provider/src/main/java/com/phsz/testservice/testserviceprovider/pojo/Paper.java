package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String content;
    @Column
    private Integer totalScore;
    @Column
    private Long durationSeconds;
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PaperQuestion> questions;
}
