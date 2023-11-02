package com.leno.bank.controllers;


import com.leno.bank.domain.user.User;
import com.leno.bank.dtos.UserDTO;
import com.leno.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(UserDTO user){

           List<User> users = this.userService.getAllUsers();
           return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
