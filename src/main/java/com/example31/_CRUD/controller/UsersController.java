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
@RequestMapping("")
public class UsersController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @GetMapping(value = "/admin")
    public String showAllUsers(ModelMap model) {
        return "listOfUsers";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userProfile";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "user_roles", required = false) String[] role) {
        return "redirect:/admin";
    }
}

/*@Controller
public class UsersController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public String showProfile(Model model, Authentication aut) {
        model.addAttribute("user", userServiceImpl.loadUserByUsername(aut.getName()));
        return "userProfile";
    }
}

 */
