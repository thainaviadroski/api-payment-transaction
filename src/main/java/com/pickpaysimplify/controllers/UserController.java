package com.pickpaysimplify.controllers;

import com.pickpaysimplify.domain.Users;
import com.pickpaysimplify.dto.UserDTO;
import com.pickpaysimplify.repositories.UserRepository;
import com.pickpaysimplify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Users> newUser(@RequestBody UserDTO user) {
        Users newUser = service.saveNewUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() throws Exception {
        List<Users> users = service.listAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
