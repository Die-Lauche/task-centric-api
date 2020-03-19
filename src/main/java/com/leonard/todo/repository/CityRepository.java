package com.leonard.todo.repository;

import com.leonard.todo.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findAll();

    Optional<City> findByName(String name);

}
