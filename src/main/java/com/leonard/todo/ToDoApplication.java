package com.leonard.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.leonard.todo.repository")
@SpringBootApplication
public class ToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

}
