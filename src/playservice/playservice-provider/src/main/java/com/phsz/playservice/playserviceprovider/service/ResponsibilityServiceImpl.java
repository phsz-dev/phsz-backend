package com.phsz.playservice.playserviceprovider.service;

import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import com.phsz.playservice.playserviceprovider.repository.ResponsibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsibilityServiceImpl {

    ResponsibilityRepository responsibilityRepository;

    @Autowired
    public ResponsibilityServiceImpl(ResponsibilityRepository responsibilityRepository) {
        this.responsibilityRepository = responsibilityRepository;
    }

    public Responsibility getResponsibilityById(Long id) {
        return responsibilityRepository.findById(id).orElse(null);
    }

    public Page<Responsibility> getResponsibilityByRole(Role role, Pageable pageable) {
        return responsibilityRepository.findAllByRole(role, pageable);
    }

    public String addResponsibility(Responsibility responsibility) {
        Optional<Responsibility> byId = responsibilityRepository.findById(responsibility.getId());
        if (byId.isEmpty()) {
            return null;
        }
        Responsibility save = responsibilityRepository.save(responsibility);
        return save.getId().toString();
    }

    public String deleteResponsibility(Long id) {
        Optional<Responsibility> byId = responsibilityRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        Optional<Responsibility> responsibility = responsibilityRepository.deleteResponsibilityById(id);
        return responsibility.get().getId().toString();
    }

    public String updateResponsibility(Responsibility responsibility) {
        Optional<Responsibility> byId = responsibilityRepository.findById(responsibility.getId());
        if (byId.isEmpty()) {
            return null;
        }
        Responsibility save = responsibilityRepository.save(responsibility);
        return save.getId().toString();
    }


    public List<Responsibility> getFullResponsibilityByRole(Role role) {
        return responsibilityRepository.findByRole(role);
    }
}
