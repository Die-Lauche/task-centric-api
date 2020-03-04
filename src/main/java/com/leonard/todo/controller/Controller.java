package com.leonard.todo.controller;

import com.leonard.todo.model.Todo;
import com.leonard.todo.model.User;
import com.leonard.todo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping(value = "/all")
    public List<Todo> getAll() {
        return  todoRepository.findAll();
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