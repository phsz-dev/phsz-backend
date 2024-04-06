package com.phsz.clerkservice.clerkserviceprovider.service;

import com.phsz.clerkservice.clerkserviceapi.entity.Clerk;
import com.phsz.clerkservice.clerkserviceapi.service.ClerkService;
import com.phsz.clerkservice.clerkserviceprovider.repository.ClerkRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClerkServiceImpl implements ClerkService {
    @Resource
    private final ClerkRepository clerkRepository;

    public ClerkServiceImpl(ClerkRepository clerkRepository) {
        this.clerkRepository = clerkRepository;
    }

    @Override
    public List<Clerk> findAll() {
        return clerkRepository.findAll();
    }

    @Override
    public Optional<Clerk> findById(Long clerkId) {
        return clerkRepository.findById(clerkId);
    }

    @Override
    public Optional<Clerk> findByName(String clerkName) {
        return clerkRepository.findByName(clerkName);
    }

    @Override
    public String save(Clerk clerk) {
        clerkRepository.save(clerk);
        return "success";
    }

    @Override
    public String update(Clerk clerk) {
        clerkRepository.save(clerk);
        return "success";
    }

    @Override
    public String deleteById(Long clerkId) {
        clerkRepository.deleteById(clerkId);
        return "success";
    }
}