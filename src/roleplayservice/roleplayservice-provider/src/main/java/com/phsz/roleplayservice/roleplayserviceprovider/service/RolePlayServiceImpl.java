package com.phsz.roleplayservice.roleplayserviceprovider.service;

import com.phsz.roleplayservice.roleplayserviceapi.service.RolePlayService;
import com.phsz.roleplayservice.roleplayserviceprovider.entity.Role;
import com.phsz.roleplayservice.roleplayserviceprovider.repository.RoleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolePlayServiceImpl implements RolePlayService {
    @Resource
    private final RoleRepository roleRepository;

    public RolePlayServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public String save(Role role) {
        roleRepository.save(role);
        return "success";
    }

    @Override
    public String update(Role role) {
        roleRepository.update(role);
        return "success";
    }

    @Override
    public String deleteById(Long roleId) {
        roleRepository.deleteById(roleId);
        return "success";
    }
}

