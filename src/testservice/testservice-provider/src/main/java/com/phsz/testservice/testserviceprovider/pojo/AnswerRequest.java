package com.phsz.testservice.testserviceprovider.pojo;

import lombok.Data;

@Data
public class AnswerRequest {
    private Long examinationId;
    private Long questionId;
    private String answer;
}
