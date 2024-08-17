package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.Role;
import org.project.shoestoreproject.enums.EnumRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role getRoleByRoleName(EnumRoleName role);
}
