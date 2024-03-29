package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.testservice.testserviceprovider.pojo.Examination;
import com.phsz.testservice.testserviceprovider.repository.ExaminationRepository;
import com.phsz.testservice.testserviceprovider.service.ExaminationService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	@Resource
	private ExaminationRepository repository;

	public ExaminationServiceImpl(ExaminationRepository repository) {
		this.repository = repository;
	}

	@Override
	public Examination getExaminationById(Long examinationId) {
		return repository.findById(examinationId).orElse(null);
	}

	@Override
	public Page<Examination> getAllExaminations(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Examination> getExaminationsByName(String examinationName, Pageable pageable) {
		return repository.findAllByExaminationNameLike(examinationName, pageable);
	}

	@Override
	public String addExamination(Examination examination) {
		Long examinationId = examination.getExaminationId();
		if (repository.findById(examinationId).isPresent()) {
			return null;
		}
		Examination save = repository.save(examination);
		return save.getExaminationId().toString();

	}

	@Override
	public String updateExamination(Examination examination) {
		Long examinationId = examination.getExaminationId();
		if (repository.findById(examinationId).isEmpty()) {
			return null;
		}
		Examination save = repository.save(examination);
		return save.getExaminationId().toString();
	}

	@Override
	public String deleteExamination(Long examinationId) {
		Optional<Examination> examination= repository.deleteExaminationByExaminationId(examinationId);
		return examination.map(value -> value.getExaminationId().toString()).orElse(null);
	}
}
