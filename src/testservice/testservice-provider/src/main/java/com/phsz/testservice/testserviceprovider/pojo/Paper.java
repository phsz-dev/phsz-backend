package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private Integer totalScore;
    private Long durationSeconds;
    private Date deadline;

    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PaperQuestion> questions;

    // paper_user 表示有权限的用户 id
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> permUser;
}
