package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.PaperInfo;
import com.phsz.testservice.testserviceprovider.repository.PaperRepository;
import com.phsz.testservice.testserviceprovider.repository.QuestionRepository;
import com.phsz.testservice.testserviceprovider.service.PaperService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    PaperRepository paperRepository;

    @Resource
    QuestionRepository questionRepository;



    public PaperServiceImpl(PaperRepository paperRepository, QuestionRepository questionRepository) {
        this.paperRepository = paperRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Paper getPaperById(Long paperId) {
        return paperRepository.findById(paperId).orElse(null);
    }

    public PaperInfo getPaperInfoById(Long paperId) {
        return paperRepository.findPaperInfoById(paperId);
    }

    @Override
    public Page<Paper> getAllPapers(Pageable pageable) {
        return paperRepository.findAll(pageable);
    }

    @Override
    public Page<PaperInfo> getAllPaperInfo(Pageable pageable) {
        return paperRepository.findAllInfoBy(pageable);
    }

    @Override
    public Page<PaperInfo> getPapersByName(String paperName, Pageable pageable) {
        return paperRepository.findAllInfoByNameLike(paperName, pageable);
    }

    @Override
    public Long addPaper(Paper paper) {
        Long paperId = paper.getId();
        Optional<Paper> paperById = paperRepository.findById(paperId);
        if (paperById.isPresent()) {
            return null;
        }
        Paper save = paperRepository.save(paper);
        return save.getId();
    }

    @Override
    public Long updatePaper(Paper paper) {
        Long paperId = paper.getId();
        Optional<Paper> paperById = paperRepository.findById(paperId);
        if (paperById.isEmpty()) {
            return null;
        }
        return paperRepository.save(paper).getId();
    }

    @Override
    public Long deletePaper(Long paperId) {
        Optional<Paper> paper = paperRepository.deletePaperById(paperId);
        return paper.map(Paper::getId).orElse(null);
    }

}
