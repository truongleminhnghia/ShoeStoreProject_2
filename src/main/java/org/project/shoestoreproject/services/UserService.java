package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.User;

public interface UserService {
    public void save(User user);
    public User getUserByID(String userID);
    public User getUserByEmail(String email);
    public boolean getUserByPhone(String phone);
    public User getUserByUsername(String username);
}
