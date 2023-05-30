package com.kidbot.Services.impl;

import com.kidbot.Entities.User;
import com.kidbot.Repositories.UserRepository;
import com.kidbot.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User createdUser = this.userRepository.save(user);
        return createdUser;
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id).get() ;
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);

    }
}
