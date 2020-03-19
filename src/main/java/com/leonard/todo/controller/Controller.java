package com.leonard.todo.controller;

import com.leonard.todo.model.*;
import com.leonard.todo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Controller {

    private UserRepository userRepository;
    private TodoListRepository todoListRepository;
    private TodoRepository todoRepository;
    private CityRepository cityRepository;
    private GroupRepository groupRepository;

    @Autowired
    public Controller(UserRepository userRepository, TodoListRepository todoListRepository,
                      TodoRepository todoRepository, CityRepository cityRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.todoListRepository = todoListRepository;
        this.todoRepository = todoRepository;
        this.cityRepository = cityRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping(value = "checkLogin")
    public ResponseEntity<User> getUser(@RequestBody LoginResponse loginResponse){
        Optional<User> byUsername = userRepository.findByUsername(loginResponse.getUsername());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if(user.getPassword().equals(loginResponse.getPassword())){
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<User> getUser(@RequestParam Integer uid) {
        Optional<User> byId = userRepository.findById(uid);
        if (byId.isPresent()) {
            return new ResponseEntity<User>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.BAD_GATEWAY);
    }

    @GetMapping(value = "/hi")
    public String getError() {
        return "Hallo";
    }

    @PostMapping(value = "/addTodo")
    public ResponseEntity addTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
        return new ResponseEntity(HttpStatus.OK);
    }

}