package com.example31._CRUD.service;

import com.example31._CRUD.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User userById(Long id);
    void deleteUser(Long id);
    List<User> listOfUsers();
    User updateUser(User user);
}
