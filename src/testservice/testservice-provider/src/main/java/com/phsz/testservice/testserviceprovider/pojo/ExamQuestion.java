package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExamQuestion {
    @Id
    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;
}
