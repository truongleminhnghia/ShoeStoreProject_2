package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.User;

public interface UserService {
    public void save(User user);
    public User getUserByID(String userID);
    public User getUserByEmail(String email);
    public boolean getUserByPhone(String phone);
}
