package com.leonard.todo.controller;

import com.leonard.todo.model.*;
import com.leonard.todo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Controller {

    private UserRepository userRepository;
    private TodoListRepository todoListRepository;
    private TodoRepository todoRepository;
    private GroupRepository groupRepository;

    @Autowired
    public Controller(UserRepository userRepository, TodoListRepository todoListRepository,
                      TodoRepository todoRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.todoListRepository = todoListRepository;
        this.todoRepository = todoRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping(value = "/checkLogin")
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
        Optional<Group> normalGroup = groupRepository.findById(2);
        Group group = normalGroup.get();
        user.setGroup(group);
        user.setDate(new Date());
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //This has to go away
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

    //TodoLists

    @PostMapping(value = "/addTodoList")
    public ResponseEntity addTodoList(@RequestBody TodoList todoList) {
        todoList.setDate(new Date());
        todoListRepository.save(todoList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getTodoList")
    public ResponseEntity getTodoList(@RequestParam Integer uid) {
        Optional<User> byId = userRepository.findById(uid);
        User userById = byId.get();
        Set<TodoList> todoLists = userById.getTodoLists();
        return new ResponseEntity(todoLists,HttpStatus.OK);
    }

    @GetMapping(value = "/todosForList")
    public ResponseEntity getTodosForList(@RequestParam Integer listId) {
        Optional<TodoList> byId = todoListRepository.findById(listId);
        TodoList todoListById = byId.get();
        Set<Todo> todos = todoListById.getTodos();
        return new ResponseEntity(todos,HttpStatus.OK);
    }

    @PatchMapping(value = "/updateTodoList")
    public ResponseEntity updateTodoList(@RequestBody TodoList todoList) {
        Optional<TodoList> byId = todoListRepository.findById(todoList.getId());
        if (byId.isPresent()){
            TodoList todoById = byId.get();
            todoById.setName(todoList.getTitle());
            todoById.setUser(todoList.getUser());
            todoListRepository.save(todoById);
            return new ResponseEntity(HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteTodoList")
    public ResponseEntity deleteTodoList(@RequestParam Integer listid) {
        todoListRepository.deleteById(listid);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Todos

    @GetMapping(value = "/getTodo")
    public ResponseEntity getTodo(@RequestParam Integer tid) {
        Optional<Todo> byId = todoRepository.findById(tid);
        Todo todoById = byId.get();
        return new ResponseEntity(todoById,HttpStatus.OK);
    }

    @PostMapping(value = "/addTodo")
    public ResponseEntity addTodo(@RequestBody Todo todo) {
        todo.setDate(new Date());
        todo.setUpdateDate(new Date());
        todoRepository.save(todo);
        return new ResponseEntity(todo, HttpStatus.OK);
    }

    @PatchMapping(value = "/updateTodo")
    public ResponseEntity updateTodo(@RequestBody Todo todo) {
        Optional<Todo> byId = todoRepository.findById(todo.getId());
        if (byId.isPresent()){
            Todo todoById = byId.get();
            todoById.setContent(todo.getContent());
            todoById.setIsCompleted(todo.getIsCompleted());
            todoById.setIsInProgress(todo.getIsInProgress());
            todoById.setIsInTodo(todo.getIsInTodo());
            todoById.setUpdateDate(new Date());
            todoRepository.save(todoById);
            return new ResponseEntity(HttpStatus.OK);

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteTodo")
    public ResponseEntity deleteTodo(@RequestParam Integer tid) {
        todoRepository.deleteById(tid);
        return new ResponseEntity(HttpStatus.OK);
    }
}