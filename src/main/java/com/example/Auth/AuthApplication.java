package com.example.Auth;

import com.example.Auth.entities.Role;
import com.example.Auth.entities.User;
import com.example.Auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	public void run(String... args){
		System.out.println("HERE?");
	}

}
