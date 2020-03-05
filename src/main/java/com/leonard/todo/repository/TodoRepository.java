package com.leonard.todo.repository;

import com.leonard.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

    List<Todo> findAll();

    List<Todo> findByTodoListId(Integer listId);

}
