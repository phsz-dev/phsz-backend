package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String status;
    @Column
    private Date startTime;
    @Column
    private Date endTime;
    @Column
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;
}
