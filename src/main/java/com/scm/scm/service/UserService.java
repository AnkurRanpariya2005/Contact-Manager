package com.scm.scm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.scm.scm.entity.User;


public interface UserService {
    
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExits(String id);

    boolean isUserExitsByEmail(String email);

    List<User> getAllUsers();

    User getByEmail(String email);

}
