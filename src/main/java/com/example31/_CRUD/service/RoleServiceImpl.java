package com.example31._CRUD.service;

import com.example31._CRUD.model.Role;
import com.example31._CRUD.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> loadRoleFromDB() {
        return roleRepository.findAll();
    }

}
