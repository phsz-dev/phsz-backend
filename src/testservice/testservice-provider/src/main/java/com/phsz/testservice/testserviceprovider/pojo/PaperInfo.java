package com.phsz.testservice.testserviceprovider.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PaperInfo {

    private Long paperId;
    private String paperName;
    private List<Question> content;
    private float totalScore;
    private String timeLimit;

    public PaperInfo PaperInfoCons(Paper paper, List<Question> content) {
        this.paperId = paper.getId();
        this.paperName = paper.getPaperName();
        this.totalScore = paper.getTotalScore();
        this.timeLimit = paper.getDurationSeconds().toString();
        this.content = content;
        return this;
    }
}
