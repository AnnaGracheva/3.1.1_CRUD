package com.example31._CRUD.controller;

import com.example31._CRUD.model.User;
import com.example31._CRUD.service.RoleServiceImpl;
import com.example31._CRUD.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String listOfUsers(Model model) {
        model.addAttribute("users", userServiceImpl.listOfUsers());
        model.addAttribute("allRoles", roleServiceImpl.loadRoleFromDB());
        return "listOfUsers";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.userById(id));
        return "userById";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "user_roles", required = false) String[] rolesFromView) {
        System.out.println(user);
        for (String s : rolesFromView) {
            if (s.equals("ROLE_ADMIN")) {
                user.addRoleToUser(roleServiceImpl.loadRoleFromDB().get(0));
            } else if (s.equals("ROLE_USER")) {
                user.addRoleToUser(roleServiceImpl.loadRoleFromDB().get(1));
            }
        }
        userServiceImpl.saveUser(user);
        System.out.println(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.userById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        User userToUpdate = userServiceImpl.userById(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userServiceImpl.updateUser(userToUpdate);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }
}
