package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Role;
import org.project.shoestoreproject.Enum.EnumRoleName;
import org.project.shoestoreproject.Repositories.RoleRepository;
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
