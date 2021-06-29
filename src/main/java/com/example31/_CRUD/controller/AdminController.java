package com.example31._CRUD.controller;

import com.example31._CRUD.model.User;
import com.example31._CRUD.service.RoleServiceImpl;
import com.example31._CRUD.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/users")
    public List<User> listOfUsers() {
        return userServiceImpl.listOfUsers();
    }

    @GetMapping("/{id}")
    public User user(@PathVariable Long id) {
       return userServiceImpl.userById(id);
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        return userServiceImpl.saveUser(user);
    }

    @PatchMapping("/edit")
    public User updateUser(@RequestBody User user) {
        return userServiceImpl.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
    }
}
