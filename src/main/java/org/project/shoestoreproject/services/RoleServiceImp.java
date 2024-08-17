package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Role;
import org.project.shoestoreproject.enums.EnumRoleName;
import org.project.shoestoreproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired private RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(EnumRoleName roleName) {
        Role role = roleRepository.getRoleByRoleName(roleName);
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
