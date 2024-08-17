package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Role;
import org.project.shoestoreproject.enums.EnumRoleName;

import java.util.List;

public interface RoleService {
    public Role getRoleByRoleName(EnumRoleName roleName);

    public List<Role> getAllRoles();
}
