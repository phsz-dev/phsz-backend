package com.phsz.caseservice.caseserviceprovider.controller;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.Result;
import com.phsz.caseservice.caseserviceprovider.service.Impl.CaseServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	Result result;
	//查找所有病例
	@GetMapping
	Result getAllCase(@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		ArrayList<Case> allCase = caseService.findAllCase(pageable);
		result.setCode(1);
		result.setData(allCase);
		result.setMessage("successfully");
		return result;
	}
	//添加病例
	@PostMapping
	Result addNewCase(@RequestBody Case case1) {
		result.setData(caseService.addNewCase(case1));
		if(caseService.addNewCase(case1)==null){
			result.setCode(0);
			result.setMessage("already exists");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	//删除病例
	@DeleteMapping("/{id}")
	Result deleteCaseById(@PathVariable Long id){
		String s = caseService.deleteCase(id);
		if(s==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setData(s);
		result.setCode(1);
		result.setMessage("successfully");
		return result;

	}
	//修改病例
	@PutMapping("/{id}")
	Result updateCaseById(@PathVariable Long id,@RequestBody Case case1){
		result.setData(caseService.updateCase(case1));
		if (caseService.updateCase(case1)==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	//查找病例
	@GetMapping("/{id}")
	Result findCaseById(@PathVariable Long id){
		Case aCase = caseService.findCase(id);
		if(aCase==null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setData(aCase);
		result.setMessage("successfully");
		return result;
	}

	//根据名称查找病例
	@GetMapping("/name")
	Result findCaseByName(@RequestParam("caseName") String caseName,@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		result.setData(caseService.findAllByCaseNameLike(caseName,pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
	//根据疾病名称查询病例
	@GetMapping("/illness")
	Result findCaseByIllnessId(@RequestParam("illnessId") Long illnessId,@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum){
		Pageable pageable= PageRequest.of(pageNum,pageSize);
		result.setData(caseService.findAllByIllnessId(illnessId,pageable));
		result.setCode(1);
		result.setMessage("successfully");
		return result;
	}
}
