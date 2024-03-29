package com.phsz.testservice.testserviceprovider.service;

import com.phsz.testservice.testserviceprovider.pojo.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
	Page<Question> findAllByContentLike(String content, Pageable pageable);
	Page<Question> findAllByType(String type, Pageable pageable);
	Question deleteQuestionByQuestionId(Long questionId);
	Question getQuestionByQuestionId(Long questionId);
	String addQuestion(Question question);
	Page<Question> findAll(Pageable pageable);
	String updateQuestion(Question question);
}
