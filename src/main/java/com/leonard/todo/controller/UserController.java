package com.leonard.todo.controller;

import com.leonard.todo.model.User;
import com.leonard.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAll() {
        return  new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/load")
    public List<User> persist(@RequestBody final User user) {
        userRepository.save(user);
        return userRepository.findAll();
    }
}
