package com.phsz.playservice.playserviceprovider.service;

import com.phsz.playservice.playserviceprovider.pojo.LearnedSubResponsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import com.phsz.playservice.playserviceprovider.repository.LearnedSubResponsibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnedSubResponsibilityServiceImpl {

    private final LearnedSubResponsibilityRepository learnedSubResponsibilityRepository;

    @Autowired
    LearnedSubResponsibilityServiceImpl(LearnedSubResponsibilityRepository learnedSubResponsibilityRepository){
        this.learnedSubResponsibilityRepository = learnedSubResponsibilityRepository;
    }

    public LearnedSubResponsibility addLearned(long subId, long userId) {
        LearnedSubResponsibility learnedSubResponsibility = new LearnedSubResponsibility();
        learnedSubResponsibility.setSubResponsibilityId(subId);
        learnedSubResponsibility.setUserId(userId);
        return learnedSubResponsibilityRepository.save(learnedSubResponsibility);
    }

    public List<Long> getLearned(long l, Role role) {
        List<LearnedSubResponsibility> lblist =  learnedSubResponsibilityRepository.findByUserId(l,role);
        return lblist.stream().map(LearnedSubResponsibility::getSubResponsibilityId).toList();
    }
}
