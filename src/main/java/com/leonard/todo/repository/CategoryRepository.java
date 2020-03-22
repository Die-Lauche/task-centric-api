package com.leonard.todo.repository;

import com.leonard.todo.model.Category;
import com.leonard.todo.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<Category> findById(Integer id);

    List<Category> findAll();

}
