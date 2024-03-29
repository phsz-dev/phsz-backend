package com.phsz.testservice.testserviceprovider.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PaperInfo {

	private Long paperId;
	private String paperName;
	private List<Question> content;
	private float totalScore;
	private String timeLimit;

	public PaperInfo PaperInfoCons(Paper paper, List<Question> content) {
		this.paperId=paper.getPaperId();
		this.paperName=paper.getPaperName();
		this.totalScore=paper.getTotalScore();
		this.timeLimit=paper.getTimeLimit();
		this.content=content;
		return this;
	}
}
