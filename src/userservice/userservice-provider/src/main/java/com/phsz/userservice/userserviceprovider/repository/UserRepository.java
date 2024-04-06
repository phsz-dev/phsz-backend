package com.phsz.userservice.userserviceprovider.repository;

import com.phsz.userservice.userserviceprovider.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    Page<AppUser> findAll(Pageable pageable);
}
