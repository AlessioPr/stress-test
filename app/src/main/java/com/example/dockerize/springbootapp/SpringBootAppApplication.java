package com.example.dockerize.springbootapp;

import com.example.dockerize.springbootapp.document.Users;
import com.example.dockerize.springbootapp.repository.UserRepository;
import java.util.Random;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringBootAppApplication {

	private final UserRepository userRepository;

	public SpringBootAppApplication(
		UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/createUser")
	public String hello(@RequestBody Users user) {
		Users saved = null;
		try {
			user.setId(user.getId() + new Random().nextInt());
			saved = userRepository.save(user);
		} catch (Exception e) {
			return "Error occurred: " + e.getMessage();
		}
		return saved.toString();
	}



	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
}
