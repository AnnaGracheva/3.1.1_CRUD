package com.example31._CRUD.service;

import com.example31._CRUD.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User userById(Long id);
    void deleteUser(Long id);
    List<User> listOfUsers();
    void updateUser(User user);
}
