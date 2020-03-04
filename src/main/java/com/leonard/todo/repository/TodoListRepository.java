package com.leonard.todo.repository;

import com.leonard.todo.model.TodoList;
import com.leonard.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {

    List<TodoList> findByUser(User user);

    List<TodoList> findAll();
}