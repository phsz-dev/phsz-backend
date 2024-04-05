package com.phsz.caseservice.caseserviceprovider.service;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.CaseInfo;
import com.phsz.caseservice.caseserviceprovider.pojo.RoughCaseInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface CaseService {
	//增加新病例
	String addNewCase(CaseInfo case1);
	//删除病例
	String deleteCase(Long caseId);
	//修改病例
	String updateCase(CaseInfo case1);
	//查找病例
	CaseInfo findCase(Long caseId);
	//查找所有病例
	ArrayList<Case> findAllCase(Pageable pageable);
	//根据病例名查找病例
	Page<Case> findAllByCaseNameLike(String caseName, Pageable pageable);

	Page<RoughCaseInfoDto> findRoughCaseListByDiseaseId(Long diseaseId, Pageable pageable);
}
