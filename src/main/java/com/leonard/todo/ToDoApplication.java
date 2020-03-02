package com.leonard.todo;

import com.leonard.todo.model.User;
import com.leonard.todo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.leonard.todo.repository")
@SpringBootApplication
public class ToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

	public CommandLineRunner command(UserRepository userRepository) {
		return (args -> {
			for (User user : userRepository.findAll()) {
				System.out.println(user);
			}
		});
	}
}
