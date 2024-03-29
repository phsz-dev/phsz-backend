package com.phsz.caseservice.caseserviceprovider.service;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface CaseService {
	//增加新病例
	String addNewCase(Case case1);
	//删除病例
	String deleteCase(Long caseId);
	//修改病例
	String updateCase(Case case1);
	//查找病例
	Case findCase(Long caseId);
	//查找所有病例
	ArrayList<Case> findAllCase(org.springframework.data.domain.Pageable pageable);
	//根据病例名查找病例
	Page<Case> findAllByCaseNameLike(String caseName, org.springframework.data.domain.Pageable pageable);

	Page<Case> findAllByIllnessId(Long illnessId, org.springframework.data.domain.Pageable pageable);
}
