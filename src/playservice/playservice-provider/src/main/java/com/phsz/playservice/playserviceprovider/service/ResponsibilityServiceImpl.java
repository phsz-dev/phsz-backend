package com.phsz.playservice.playserviceprovider.service;

import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.repository.ResponsibilityRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponsibilityServiceImpl {

	@Resource
	ResponsibilityRepository responsibilityRepository;
	public ResponsibilityServiceImpl(ResponsibilityRepository responsibilityRepository) {
		this.responsibilityRepository = responsibilityRepository;
	}

	public Responsibility getResponsibilityById(Long id) {
		return responsibilityRepository.findById(id).orElse(null);
	}

	public Page<Responsibility> getResponsibilityByRole(String role, Pageable pageable) {
		return responsibilityRepository.findAllByRole(role, pageable);
	}

	public String addResponsibility(Responsibility responsibility) {
		Optional<Responsibility> byId = responsibilityRepository.findById(responsibility.getResponsibilityId());
		if(byId.isEmpty()) {
			return null;
		}
		Responsibility save = responsibilityRepository.save(responsibility);
		return save.getResponsibilityId().toString();
	}

	public String deleteResponsibility(Long id) {
		Optional<Responsibility> byId = responsibilityRepository.findById(id);
		if(byId.isEmpty()) {
			return null;
		}
		Optional<Responsibility> responsibility = responsibilityRepository.deleteResponsibilityByResponsibilityId(id);
		return responsibility.get().getResponsibilityId().toString();
	}
	public String updateResponsibility(Responsibility responsibility) {
		Optional<Responsibility> byId = responsibilityRepository.findById(responsibility.getResponsibilityId());
		if (byId.isEmpty()) {
			return null;
		}
		Responsibility save = responsibilityRepository.save(responsibility);
		return save.getResponsibilityId().toString();
	}
}
