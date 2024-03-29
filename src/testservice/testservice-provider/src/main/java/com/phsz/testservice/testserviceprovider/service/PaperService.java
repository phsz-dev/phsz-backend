package com.phsz.testservice.testserviceprovider.service;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.PaperInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaperService {
	PaperInfo getPaperById(Long paperId);
	Page<Paper> getAllPapers(Pageable pageable);
	Page<PaperInfo> getPapersByName(String paperName, Pageable pageable);
	String addPaper(Paper paper);
	String updatePaper(Paper paper);
	String deletePaper(Long paperId);
}
