package org.project.shoestoreproject.Repositories;

import org.project.shoestoreproject.Entity.Role;
import org.project.shoestoreproject.Enum.EnumRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role getRoleByRoleName(EnumRoleName role);
}
