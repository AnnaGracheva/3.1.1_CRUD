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
    public String listOfUsers(ModelMap model) {
        model.addAttribute("users", userServiceImpl.listOfUsers());
        model.addAttribute("allRoles", roleServiceImpl.loadRoleFromDB());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleServiceImpl.loadRoleFromDB());
        return "listOfUsers";
    }

    @GetMapping(value = "/user")
    public String user(Model model, Authentication aut) {
        model.addAttribute("user", userServiceImpl.loadUserByUsername(aut.getName()));
        return "userProfile";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user, @RequestParam(value = "user_roles", required = false) String[] rolesFromView) {
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

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id, @RequestParam(value = "user_roles", required = false) String[] rolesFromView) {
        User userToUpdate = userServiceImpl.userById(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        System.out.println(user);
        for (String s : rolesFromView) {
            if (s.equals("ROLE_ADMIN")) {
                userToUpdate.addRoleToUser(roleServiceImpl.loadRoleFromDB().get(0));
            } else if (s.equals("ROLE_USER")) {
                userToUpdate.addRoleToUser(roleServiceImpl.loadRoleFromDB().get(1));
            }
        }
        userServiceImpl.updateUser(userToUpdate);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }
}
