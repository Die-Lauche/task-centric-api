package com.leonard.todo.repository;

import com.leonard.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Integer id);

    List<User> findAll();

}
