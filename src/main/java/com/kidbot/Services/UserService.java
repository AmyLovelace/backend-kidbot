package com.kidbot.Services;

import com.kidbot.Entities.User;

import java.util.List;

public interface UserService {
     List<User> findAllUsers();
     User createUser(User user);
     User updateUser(User user);
     User findUserById(Long id);
     void deleteUserById(Long id);
}
