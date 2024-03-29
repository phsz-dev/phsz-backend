package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.testservice.testserviceprovider.pojo.Question;
import com.phsz.testservice.testserviceprovider.repository.QuestionRepository;
import com.phsz.testservice.testserviceprovider.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class QuestionServiceImpl implements QuestionService {
	@Resource
	QuestionRepository questionRepository;
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	@Override
	public Page<Question> findAllByContentLike(String content, Pageable pageable) {
		return questionRepository.findAllByContentLike(content, pageable);
	}

	@Override
	public Page<Question> findAllByType(String type, Pageable pageable) {
		return questionRepository.findAllByType(type, pageable);
	}

	@Override
	public Question deleteQuestionByQuestionId(Long questionId) {
		return questionRepository.deleteQuestionByQuestionId(questionId).orElse(null);
	}

	@Override
	public Question getQuestionByQuestionId(Long questionId) {
		return questionRepository.findById(questionId).orElse(null);
	}

	@Override
	public String addQuestion(Question question) {
		Long questionId = question.getQuestionId();
		if (questionRepository.findById(questionId).isPresent()){
			return null;
		}
		Question save = questionRepository.save(question);
		return save.getQuestionId().toString();
	}

	@Override
	public Page<Question> findAll(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}

	@Override
	public String updateQuestion(Question question) {
		Long questionId = question.getQuestionId();
		if (questionRepository.findById(questionId).isEmpty()){
			return null;
		}
		Question save = questionRepository.save(question);
		return save.getQuestionId().toString();
	}
}
