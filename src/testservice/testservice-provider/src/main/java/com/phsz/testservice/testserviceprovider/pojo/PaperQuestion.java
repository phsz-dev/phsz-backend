package com.phsz.testservice.testserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PaperQuestion {
    @Id @ManyToOne @JoinColumn(name = "paper_id")
    @JsonIgnore
    private Paper paper;

    @Id @ManyToOne @JoinColumn(name = "question_id")
    private Question question;

    private Integer sequence; // 问题的顺序
    private Integer score; // 问题的分数
}
