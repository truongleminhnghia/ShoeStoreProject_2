package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Role;
import org.project.shoestoreproject.Enum.EnumRoleName;

import java.util.List;

public interface RoleService {
    public Role getRoleByRoleName(EnumRoleName roleName);

    public List<Role> getAllRoles();
}
