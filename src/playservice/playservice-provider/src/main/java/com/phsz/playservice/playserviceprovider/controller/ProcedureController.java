package com.phsz.playservice.playserviceprovider.controller;

import com.phsz.playservice.playserviceprovider.pojo.Procedure;
import com.phsz.playservice.playserviceprovider.pojo.Result;
import com.phsz.playservice.playserviceprovider.service.ProcedureServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plays/procedures")
public class ProcedureController {
	@Resource
	ProcedureServiceImpl procedureService;
	@Autowired
	Result result;

	public ProcedureController(ProcedureServiceImpl procedureService) {
		this.procedureService = procedureService;
	}

	@GetMapping("/{procedureId}")
	public Result getProcedureById(@PathVariable Long procedureId) {
		Procedure procedureById = procedureService.getProcedureById(procedureId);
		if(procedureById == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
	@GetMapping("/responsibility")
	public Result getProcedureByResponsibility(Long responsibility, Pageable pageable){
		Page<Procedure> procedureByResponsibility = procedureService.getProcedureByResponsibility(responsibility, pageable);
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
	@PostMapping
	public Result addProcedure(@RequestBody Procedure procedure) {
		String s = procedureService.addProcedure(procedure);
		if(s == null){
			result.setCode(0);
			result.setMessage("already exists");
			return result;
		}
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
	@DeleteMapping("/{procedureId}")
	public Result deleteProcedure(@PathVariable Long procedureId) {
		String s = procedureService.deleteProcedure(procedureId);
		if(s == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
	@GetMapping("/name")
	public Result getProcedureByName(String name, Pageable pageable){
		Page<Procedure> procedureByName = procedureService.getProcedureByName(name, pageable);
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
	@PutMapping
	public Result updateProcedure(@RequestBody Procedure procedure) {
		String s = procedureService.updateProcedure(procedure);
		if(s == null){
			result.setCode(0);
			result.setMessage("not found");
			return result;
		}
		result.setCode(1);
		result.setMessage("success");
		return result;
	}
}
