package com.phsz.clerkservice.clerkserviceapi.service;

import com.phsz.clerkservice.clerkserviceprovider.entity.Clerk;

import java.util.List;
import java.util.Optional;

public interface ClerkService {
    List<Clerk> findAll();
    Optional<Clerk> findById(Long clerkId);
    Optional<Clerk> findByName(String clerkName);
    String save(Clerk clerk);
    String update(Clerk clerk);
    String deleteById(Long clerkId);
}