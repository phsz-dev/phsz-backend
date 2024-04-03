package com.phsz.playservice.playserviceprovider.service;

import com.phsz.playservice.playserviceprovider.pojo.Procedure;
import com.phsz.playservice.playserviceprovider.repository.ProcedureRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcedureServiceImpl {
	@Resource
	ProcedureRepository procedureRepository;

	public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
		this.procedureRepository = procedureRepository;
	}

	public Procedure getProcedureById(Long id) {
		return procedureRepository.findById(id).orElse(null);
	}

	public Page<Procedure> getProcedureByResponsibility(Long responsibility, Pageable pageable){
		return procedureRepository.findAllByResponsibilityId(responsibility, pageable);
	}
	public String addProcedure(Procedure procedure) {
		Optional<Procedure> byId = procedureRepository.findById(procedure.getId());
		if(byId.isEmpty()){
			return null;
		}
		Procedure save = procedureRepository.save(procedure);
		return save.getId().toString();
	}

	public String deleteProcedure(Long id) {
		Optional<Procedure> byId = procedureRepository.findById(id);
		if(byId.isEmpty()){
			return null;
		}
		Optional<Procedure> procedure = procedureRepository.deleteProcedureById(id);
		return procedure.get().getId().toString();
	}
	public Page<Procedure> getProcedureByName(String name, Pageable pageable){
		return procedureRepository.findAllByNameLike(name, pageable);
	}
	public String updateProcedure(Procedure procedure) {
		Optional<Procedure> byId = procedureRepository.findById(procedure.getId());
		if(byId.isEmpty()){
			return null;
		}
		Procedure save = procedureRepository.save(procedure);
		return save.getId().toString();
	}
}
