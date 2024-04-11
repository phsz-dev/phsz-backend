package com.phsz.playservice.playserviceprovider.service;

import com.phsz.playservice.playserviceprovider.pojo.LearnedSubResponsibility;
import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import com.phsz.playservice.playserviceprovider.repository.LearnedSubResponsibilityRepository;
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

    LearnedSubResponsibilityRepository learnedSubResponsibilityRepository;

    @Autowired
    public ResponsibilityServiceImpl(ResponsibilityRepository responsibilityRepository, LearnedSubResponsibilityRepository learnedSubResponsibilityRepository) {
        this.responsibilityRepository = responsibilityRepository;
        this.learnedSubResponsibilityRepository = learnedSubResponsibilityRepository;
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

    public List<Long> getLearned(long l, Role inRole) {
        // 判断该Responsibility下的subResponsibility是否已经都学习，都学习了就将该Responsibility的id加入列表
        List<Long> res = responsibilityRepository.findLearnedResponsibility(l, inRole);
        return res;
    }
}
