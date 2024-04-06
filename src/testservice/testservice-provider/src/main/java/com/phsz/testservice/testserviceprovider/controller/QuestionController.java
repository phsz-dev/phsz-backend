package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.testservice.testserviceprovider.pojo.Question;
import com.phsz.testservice.testserviceprovider.service.Impl.QuestionServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/question")
public class QuestionController {
    @Resource
    QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Result findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("success", questionService.findAll(pageable).getContent());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Long id) {
        Question question = questionService.getQuestionByQuestionId(id);
        if (question == null) {
            return Result.error("not found");
        }
        return Result.success("success", question);
    }

    @PostMapping
    public Result addQuestion(@RequestBody Question question) {
        String s = questionService.addQuestion(question);
        if (s == null) {
            return Result.error("already exists");
        }
        return Result.success("success", s);
    }

    @PutMapping
    public Result updateQuestion(@RequestBody Question question) {
        String s = questionService.updateQuestion(question);
        if (s == null) {
            return Result.error("not found");
        }
        return Result.success("success", s);
    }

    @DeleteMapping("/{id}")
    public Result deleteQuestion(@PathVariable("id") Long id) {
        Question question = questionService.deleteQuestionByQuestionId(id);
        if (question == null) {
            return Result.error("not found");
        }
        return Result.success("success", question);
    }

    @GetMapping("/content")
    public Result getQuestionByContent(@RequestParam("content") String content, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("success", questionService.findAllByContentLike(content, pageable));
    }

    @GetMapping("/type")
    public Result getQuestionByType(@RequestParam("type") String type, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("success", questionService.findAllByType(type, pageable));
    }
}
