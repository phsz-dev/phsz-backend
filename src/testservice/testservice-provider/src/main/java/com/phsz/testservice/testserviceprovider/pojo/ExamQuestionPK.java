package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ExamQuestionPK implements Serializable {
    @Column(name = "examination_id")
    @JoinColumn(name = "examination_id")
    private Long examination;
    @Column(name = "question_id")
    @JoinColumn(name = "question_id")
    private Long question;
}
