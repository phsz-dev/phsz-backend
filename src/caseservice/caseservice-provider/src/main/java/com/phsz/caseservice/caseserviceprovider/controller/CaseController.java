package com.phsz.caseservice.caseserviceprovider.controller;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.CaseInfo;
import com.phsz.caseservice.caseserviceprovider.service.Impl.CaseServiceImpl;
import com.phsz.common.Result;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
	@Resource
	private CaseServiceImpl caseService;

	public CaseController(CaseServiceImpl caseService) {
		this.caseService = caseService;
	}
	//查找所有病例
	@GetMapping
	public Result getAllCase(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		ArrayList<Case> allCase = caseService.findAllCase(pageable);
		if(allCase==null){
			return Result.error("not found");
		}
		return Result.success("find all cases successfully",allCase);
	}
	//添加病例
	@PostMapping
	public Result addNewCase(@RequestBody CaseInfo case1) {
		String s = caseService.addNewCase(case1);
		if(s==null){
			return Result.error("add case failed");
		}
		return Result.success("add case successfully",null);
	}
	//删除病例
	@DeleteMapping("/{id}")
	public Result deleteCaseById(@PathVariable Long id){
		String s = caseService.deleteCase(id);
		if (s==null){
			return Result.error("not found or error deleting");
		}
		return Result.success("delete case successfully",null);
	}
	//修改病例
	@PutMapping
	public Result updateCaseById(@RequestBody CaseInfo case1){
		String s = caseService.updateCase(case1);
		if(s==null){
			return Result.error("not found or error updating");
		}
		return Result.success("update case successfully",null);
	}
	//查找病例
	@GetMapping("/{id}")
	public Result findCaseById(@PathVariable Long id){
		CaseInfo aCase = caseService.findCase(id);
		if(aCase==null){
			return Result.error("not found");
		}
		return Result.success("find case successfully",aCase);
	}

	//根据名称查找病例
	@GetMapping("/name")
	public Result findCaseByName(@RequestParam("caseName") String caseName,@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		return Result.success("find case by name successfully",caseService.findAllByCaseNameLike(caseName,pageable));
	}
	//根据疾病名称查询病例
	@GetMapping("/illness")
	public Result findCaseByIllnessId(@RequestParam("illnessId") Long illnessId,@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		return Result.success("find case by illnessId successfully",caseService.findAllByIllnessId(illnessId,pageable));
	}
}
