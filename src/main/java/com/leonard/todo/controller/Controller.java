package com.leonard.todo.controller;

import com.leonard.todo.model.User;
import com.leonard.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return  userRepository.findAll();
    }

    @GetMapping(value = "/hi")
    public String getError() {
        return "Hallo";
    }

    @PostMapping(value = "/load")
    public List<User> persist(@RequestBody final User user) {
        userRepository.save(user);
        return userRepository.findAll();
    }
}