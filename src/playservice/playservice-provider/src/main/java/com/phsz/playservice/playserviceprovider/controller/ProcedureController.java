package com.phsz.playservice.playserviceprovider.controller;


import com.phsz.playservice.playserviceprovider.pojo.Procedure;
import com.phsz.playservice.playserviceprovider.service.ProcedureServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.phsz.common.Result;

@RestController
@RequestMapping("/api/plays/procedures")
public class ProcedureController {
	ProcedureServiceImpl procedureService;

	@Autowired
	public ProcedureController(ProcedureServiceImpl procedureService) {
		this.procedureService = procedureService;
	}

	@GetMapping("/{procedureId}")
	public Result getProcedureById(@PathVariable Long procedureId) {
		Procedure procedureById = procedureService.getProcedureById(procedureId);
		if(procedureById == null){
			return Result.error("not found");
		}
		return Result.success("getProcedureById successfully",procedureById);
	}
	@GetMapping("/responsibility")
	public Result getProcedureByResponsibility(Long responsibility, Pageable pageable){
		Page<Procedure> procedureByResponsibility = procedureService.getProcedureByResponsibility(responsibility, pageable);
		if(procedureByResponsibility == null){
			return Result.error("not found");
		}
		return Result.success("getProcedureByResponsibility successfully",procedureByResponsibility);
	}
	@PostMapping
	public Result addProcedure(@RequestBody Procedure procedure) {
		String s = procedureService.addProcedure(procedure);
		if(s == null){
			return Result.error("already exists");
		}
		return Result.success("addProcedure successfully",null);
	}
	@DeleteMapping("/{procedureId}")
	public Result deleteProcedure(@PathVariable Long procedureId) {
		String s = procedureService.deleteProcedure(procedureId);
		if(s == null){
			return Result.error("not found");
		}
		return Result.success("deleteProcedure successfully",null);
	}
	@GetMapping("/name")
	public Result getProcedureByName(String name, Pageable pageable){
		Page<Procedure> procedureByName = procedureService.getProcedureByName(name, pageable);
		if(procedureByName == null){
			return Result.error("not found");
		}
		return Result.success("getProcedureByName successfully",procedureByName);
	}
	@PutMapping
	public Result updateProcedure(@RequestBody Procedure procedure) {
		String s = procedureService.updateProcedure(procedure);
		if(s == null){
			return Result.error("not found");
		}
		return Result.success("updateProcedure successfully",null);
	}
}
