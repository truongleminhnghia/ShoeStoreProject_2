package org.project.shoestoreproject.Repositories;

import org.project.shoestoreproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);

}
