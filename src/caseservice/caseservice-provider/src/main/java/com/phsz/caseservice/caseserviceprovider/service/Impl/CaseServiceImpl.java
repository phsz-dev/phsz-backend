package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.repository.CaseRepository;
import com.phsz.caseservice.caseserviceprovider.service.CaseService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {
	@Resource
	private final CaseRepository caseRepository;

	public CaseServiceImpl(CaseRepository caseRepository) {
		this.caseRepository = caseRepository;
	}

	@Override
	public String addNewCase(Case case1) {
		Long caseId = case1.getCaseId();
		if (caseRepository.findById(caseId).isPresent()){
			return null;
		}
		Case save = caseRepository.save(case1);
		return save.getCaseId().toString();
	}

	@Override
	public String deleteCase(Long caseId) {
		Optional<Case> aCase = caseRepository.deleteCaseByCaseId(caseId);
		return aCase.map(aCase1 -> aCase1.getCaseId().toString()).orElse(null);
	}

	@Override
	public String updateCase(Case case1) {
		Long caseId = case1.getCaseId();
		if (caseRepository.findById(caseId).isEmpty()){
			return null;
		}
		return caseRepository.save(case1).getCaseId().toString();
	}

	@Override
	public Case findCase(Long caseId) {
		Optional<Case> aCase = caseRepository.findById(caseId);
		return aCase.orElse(null);
	}

	@Override
	public ArrayList<Case> findAllCase(org.springframework.data.domain.Pageable pageable) {
		Page<Case> all = caseRepository.findAll(pageable);
		Collection<Case> content = all.getContent();
		return new ArrayList<>(content);

	}

	@Override
	public Page<Case> findAllByCaseNameLike(String caseName, org.springframework.data.domain.Pageable pageable) {
		return caseRepository.findAllByCaseNameLike(caseName, pageable);
	}

	@Override
	public Page<Case> findAllByIllnessId(Long illnessId, Pageable pageable) {
		return caseRepository.findAllByIllnessId(illnessId, pageable);
	}
}
