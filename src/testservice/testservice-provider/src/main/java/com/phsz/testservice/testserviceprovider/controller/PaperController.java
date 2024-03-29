package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.Result;
import com.phsz.testservice.testserviceprovider.service.Impl.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/papers")
public class PaperController {
	PaperServiceImpl paperService;

	public PaperController(PaperServiceImpl paperService) {
		this.paperService = paperService;
	}
	@Autowired
	Result result;
	@GetMapping("/{id}")
	public Result getPaperById(@PathVariable Long id) {
		result.setData(paperService.getPaperById(id));
		if(paperService.getPaperById(id)==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping
	public Result getAllPaper(@RequestParam() int pageSize, @RequestParam int pageNum) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		result.setData(paperService.getAllPapers(pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@PostMapping
	public Result addPaper(@RequestBody Paper paper) {
		result.setData(paperService.addPaper(paper));
		if(paperService.addPaper(paper)==null){
			result.setCode(0);
			result.setMessage("already exists");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@PutMapping
	public Result updatePaper(@RequestBody Paper paper) {
		Long paperId = paper.getPaperId();
		if(paperService.getPaperById(paperId)==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setData(paperService.updatePaper(paper));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@DeleteMapping("/{id}")
	public Result deletePaper(@PathVariable Long id) {
		result.setData(paperService.deletePaper(id));
		if(paperService.deletePaper(id)==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	@GetMapping("/name")
	public Result getPaperByName(@RequestParam String name, @RequestParam int pageSize, @RequestParam int pageNum) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		result.setData(paperService.getPapersByName(name, pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
}
