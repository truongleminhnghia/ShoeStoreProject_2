package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.User;
import org.project.shoestoreproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleService roleService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByID(String userID) {
        return userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByUserName(email);
    }

    @Override
    public boolean getUserByPhone(String phone) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.save(user) != null;
    }

}
