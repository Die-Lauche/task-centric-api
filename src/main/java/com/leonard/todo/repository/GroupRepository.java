package com.leonard.todo.repository;

import com.leonard.todo.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {

    List<Group> findAll();
}
