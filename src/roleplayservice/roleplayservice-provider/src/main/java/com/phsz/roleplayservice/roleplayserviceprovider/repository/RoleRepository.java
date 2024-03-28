package com.phsz.roleplayservice.roleplayserviceprovider.repository;

import com.phsz.roleplayservice.roleplayserviceprovider.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    List<Role> findAll();
    Optional<Role> findById(Long roleId);
    Role save(Role role);
    void update(Role role);
    void deleteById(Long roleId);
}
