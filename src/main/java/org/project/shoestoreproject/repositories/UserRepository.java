package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    public User getUserByUserID(@Param("userId") String userID);

}
