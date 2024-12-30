package com.scm.scm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm.entity.User;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repository.UserRepository;
import com.scm.scm.service.UserService;

@Service    
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of("ROLE_USER"));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User userFormDb = userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        userFormDb.setName(user.getName());
        userFormDb.setEmail(user.getEmail());
        userFormDb.setPassword(user.getPassword());
        userFormDb.setAbout(user.getAbout());
        userFormDb.setPhone(user.getPhone());
        userFormDb.setProfilePic(user.getProfilePic());
        userFormDb.setEnabled(user.isEnabled());
        userFormDb.setEmailVerified(user.isEmailVerified());
        userFormDb.setPhoneVerified(user.isPhoneVerified());
        userFormDb.setProvider(user.getProvider());
        userFormDb.setProviderUserId(user.getProviderUserId());
        // save the user in database
        User save = userRepository.save(userFormDb);
        return Optional.ofNullable(save);


    }

    @Override
    public void deleteUser(String id) {
        User userFromDb = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        userRepository.delete(userFromDb);
    }

    @Override
    public boolean isUserExits(String id) {
        User userFromDb = userRepository.findById(id).orElse(null);
        return userFromDb!=null?true:false;

    }

    @Override
    public boolean isUserExitsByEmail(String email) {
        User userFromDb = userRepository.findByEmail(email).orElse(null);
        return userFromDb!=null?true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
}
