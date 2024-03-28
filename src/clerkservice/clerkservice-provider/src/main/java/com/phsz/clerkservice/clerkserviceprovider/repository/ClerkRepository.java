package com.phsz.clerkservice.clerkserviceprovider.repository;

import com.phsz.clerkservice.clerkserviceprovider.entity.Clerk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClerkRepository extends CrudRepository<Clerk,Integer> {
    List<Clerk> findAll();
    Optional<Clerk> findById(Long clerkId);
    Optional<Clerk> findByName(String clerkName);
    Clerk save(Clerk clerk);
    void update(Clerk clerk);
    void deleteById(Long clerkId);
}
