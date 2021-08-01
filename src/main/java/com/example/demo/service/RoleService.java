package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    Role getByName(String name){
        return roleRepository.getByName(name).orElseThrow(NotFoundException::new);
    }
}
