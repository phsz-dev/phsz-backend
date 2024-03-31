package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.testservice.testserviceprovider.pojo.Examination;
import com.phsz.testservice.testserviceprovider.pojo.Result;
import com.phsz.testservice.testserviceprovider.service.Impl.ExaminationServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/examinations")
public class ExaminationController {
	@Resource
	ExaminationServiceImpl examinationService;

	public ExaminationController(ExaminationServiceImpl examinationService) {
		this.examinationService = examinationService;
	}
	@Autowired
	Result result;
	@GetMapping("/{examinationId}")
	public Result getExaminationById(@PathVariable Long examinationId) {
		Examination examination= examinationService.getExaminationById(examinationId);
		result.setData(examination);
		if(examination == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping
	public Result getAllExaminations(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int PageSize) {
		Pageable pageable = PageRequest.of(pageNum, PageSize);
		result.setData(examinationService.getAllExaminations(pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping("/name")
	public Result getExaminationsByName(@RequestParam("examinationName") String examinationName, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int PageSize) {
		Pageable pageable = PageRequest.of(pageNum, PageSize);
		result.setData(examinationService.getExaminationsByName(examinationName, pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@PostMapping
	public Result addExamination(@RequestBody Examination examination) {
		String s = examinationService.addExamination(examination);
		result.setData(s);
		if(s == null){
			result.setCode(0);
			result.setMessage("already exists");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@PutMapping
	public Result updateExamination(@RequestBody Examination examination) {
		String s = examinationService.updateExamination(examination);
		result.setData(s);
		if(s == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@DeleteMapping("/{examinationId}")
	public Result deleteExamination(@PathVariable Long examinationId) {
		String s = examinationService.deleteExamination(examinationId);
		result.setData(s);
		if(s == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
}
