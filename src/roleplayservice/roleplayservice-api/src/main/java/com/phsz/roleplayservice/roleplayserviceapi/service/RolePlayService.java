package com.phsz.roleplayservice.roleplayserviceapi.service;

import com.phsz.roleplayservice.roleplayserviceapi.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RolePlayService {
    List<Role> findAll();
    Optional<Role> findById(Long roleId);
    String save(Role role);
    String update(Role role);
    String deleteById(Long roleId);
}
