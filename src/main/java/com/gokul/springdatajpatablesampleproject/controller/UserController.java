package com.gokul.springdatajpatablesampleproject.controller;
import com.gokul.springdatajpatablesampleproject.model.Role;
import com.gokul.springdatajpatablesampleproject.model.User;
import com.gokul.springdatajpatablesampleproject.service.RoleService;
import com.gokul.springdatajpatablesampleproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostMapping("addRole")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PostMapping("register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
