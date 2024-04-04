package com.phsz.testservice.testserviceprovider.service;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.PaperInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaperService {
	Paper getPaperById(Long paperId);
	Page<Paper> getAllPapers(Pageable pageable);
	Page<PaperInfo> getPapersByName(String paperName, Pageable pageable);
	Long addPaper(Paper paper);
	Long updatePaper(Paper paper);
	Long deletePaper(Long paperId);
}
