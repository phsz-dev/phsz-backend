package com.phsz.playservice.playserviceprovider.controller;

import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.pojo.Result;
import com.phsz.playservice.playserviceprovider.service.ResponsibilityServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/play/responsibilities")
public class ResponsibilityController {
	@Resource
	ResponsibilityServiceImpl responsibilityService;

	@Autowired
	Result result;

	public ResponsibilityController(ResponsibilityServiceImpl responsibilityService, Result result) {
		this.responsibilityService = responsibilityService;
		this.result = result;
	}

	@GetMapping("/{responsibilityId}")
	public Result getResponsibilityById(@PathVariable Long responsibilityId) {
		Responsibility responsibilityById = responsibilityService.getResponsibilityById(responsibilityId);
		if (responsibilityById == null) {
			result.setCode(0);
			result.setMessage("not found");
			result.setData(null);
		} else {
			result.setCode(1);
			result.setMessage("success");
			result.setData(responsibilityById);
		}
		return result;
	}
	@GetMapping("/role")
	public Result getResponsibilityByRole(@RequestParam String role,@RequestParam int pageNum,@RequestParam int PageSize) {
		Pageable pageable = PageRequest.of(pageNum, PageSize);
		Page<Responsibility> responsibilityByRole = responsibilityService.getResponsibilityByRole(role, pageable);
		result.setCode(1);
		result.setMessage("success");
		result.setData(responsibilityByRole);
		return result;
	}
	@PostMapping
	public Result addResponsibility(@RequestBody Responsibility responsibility) {
		String s = responsibilityService.addResponsibility(responsibility);
		if (s == null) {
			result.setCode(0);
			result.setMessage("already exists");
			result.setData(null);
		} else {
			result.setCode(1);
			result.setMessage("success");
			result.setData(s);
		}
		return result;
	}
	@DeleteMapping("/{responsibilityId}")
	public Result deleteResponsibility(@PathVariable Long responsibilityId) {
		String s = responsibilityService.deleteResponsibility(responsibilityId);
		if (s == null) {
			result.setCode(0);
			result.setMessage("not found");
			result.setData(null);
		} else {
			result.setCode(1);
			result.setMessage("success");
			result.setData(s);
		}
		return result;
	}

	@PutMapping
	public Result updateResponsibility(@RequestBody Responsibility responsibility) {
		String s = responsibilityService.updateResponsibility(responsibility);
		if (s == null) {
			result.setCode(0);
			result.setMessage("not found");
			result.setData(null);
		} else {
			result.setCode(1);
			result.setMessage("success");
			result.setData(s);
		}
		return result;
	}
}
