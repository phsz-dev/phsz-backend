package com.phsz.testservice.testserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ExamQuestion {

    @EmbeddedId
    @JsonUnwrapped
    private ExamQuestionPK id;

    private String answer;
}
