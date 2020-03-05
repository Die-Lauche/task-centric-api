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

    @PostMapping(value = "/checkLogin")
    public ResponseEntity<LoginResponse> getUserId(@RequestBody Map<String, String> loginBody) {
        String username = loginBody.get("username");
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getPassword());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/one/todo")
    public ResponseEntity<List<Todo>> getTodosForList(@RequestParam Integer listId) {
        List<Todo> byListId = todoRepository.findByTodoListId(listId);
        return new ResponseEntity<>(byListId, HttpStatus.OK);
    }

    @GetMapping(value = "/one/todoList")
    public ResponseEntity<List<TodoList>> getTodoList(@RequestParam Integer uid) {
        List<TodoList> byUid = todoListRepository.findByUserId(uid);
        return new ResponseEntity<>(byUid, HttpStatus.OK);
    }
    @GetMapping(value = "/all/User")
    public List<User> getAllUser() {
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