package com.leno.bank.controllers;


import com.leno.bank.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(userDTO user){


    }

}
