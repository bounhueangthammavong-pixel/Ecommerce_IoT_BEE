package com.example.test.service.impl;

import com.example.test.model.Role;
import com.example.test.repository.RoleRepository;
import com.example.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll(){
        return  roleRepository.findAll();
    }

    @Override
    public Role getByName(String name){
        return roleRepository.findById(name).orElseThrow();
    }
}
