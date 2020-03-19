package com.leonard.todo.repository;

import com.leonard.todo.model.Todo;
import com.leonard.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Optional<Todo> findById(Integer id);

    List<Todo> findAll();

    List<Todo> findByTodoListId(Integer listId);

}
