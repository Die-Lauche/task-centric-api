package com.leonard.todo.repository;

import com.leonard.todo.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Integer> {

    List<Group> findAll();

    Optional<Group> findById(Integer id);

}
