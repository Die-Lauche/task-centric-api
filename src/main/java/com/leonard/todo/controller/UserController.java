package com.leonard.todo.controller;

import com.leonard.todo.model.User;
import com.leonard.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return  userRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<User> persist(@RequestBody final User user) {
        userRepository.save(user);
        return userRepository.findAll();
    }
}
