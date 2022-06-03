package com.g7.ercsystem.interfaces;

import com.g7.ercsystem.rest.auth.model.User;

import java.util.List;

public interface UserService {

    void deleteUserById(String id);
    User getUserById(String id);
    User addUser(User user);
    List<User> getAllUsers();
    List<User> getAllReviewers();
    List<User> getAllApplicants();
}
