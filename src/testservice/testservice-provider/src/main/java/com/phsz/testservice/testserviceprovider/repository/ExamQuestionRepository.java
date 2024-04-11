package com.phsz.testservice.testserviceprovider.repository;

import com.phsz.testservice.testserviceprovider.pojo.ExamQuestion;
import com.phsz.testservice.testserviceprovider.pojo.ExamQuestionPK;
import org.springframework.data.repository.CrudRepository;

public interface ExamQuestionRepository extends CrudRepository<ExamQuestion, ExamQuestionPK> { }
