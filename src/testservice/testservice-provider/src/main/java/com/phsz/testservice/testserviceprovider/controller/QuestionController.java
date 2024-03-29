package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.testservice.testserviceprovider.pojo.Question;
import com.phsz.testservice.testserviceprovider.pojo.Result;
import com.phsz.testservice.testserviceprovider.service.Impl.QuestionServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/questions")
public class QuestionController {
	@Resource
	QuestionServiceImpl questionService;
	public QuestionController(QuestionServiceImpl questionService) {
		this.questionService = questionService;
	}
	@Autowired
	Result result;
	@GetMapping
	public Result findAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		result.setData(questionService.findAll(pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping("/{id}")
	public Result findById(@PathVariable("id") Long id) {
		Question question = questionService.getQuestionByQuestionId(id);
		result.setData(question);
		System.out.println("1"+question);
		if (question==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@PostMapping
	public Result addQuestion(@RequestBody Question question) {
		result.setData(questionService.addQuestion(question));
		if(questionService.addQuestion(question) == null){
			result.setCode(0);
			result.setMessage("already exists");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}

	@PutMapping
	public Result updateQuestion(@RequestBody Question question) {
		result.setData(questionService.updateQuestion(question));
		if (questionService.updateQuestion(question) == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@DeleteMapping("/{id}")
	public Result deleteQuestion(@PathVariable("id") Long id) {
		result.setData(questionService.deleteQuestionByQuestionId(id));
		if(questionService.deleteQuestionByQuestionId(id)==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping("/content")
	public Result getQuestionByContent(@RequestParam("content") String content,@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		result.setData(questionService.findAllByContentLike(content,pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping("/type")
	public Result getQuestionByType(@RequestParam("type") String type,@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		result.setData(questionService.findAllByType(type,pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
}
