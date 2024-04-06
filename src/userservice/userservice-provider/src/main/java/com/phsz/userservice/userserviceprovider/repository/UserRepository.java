package com.phsz.userservice.userserviceprovider.repository;

import com.phsz.userservice.userserviceprovider.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser,Integer> {

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findAll();
}
