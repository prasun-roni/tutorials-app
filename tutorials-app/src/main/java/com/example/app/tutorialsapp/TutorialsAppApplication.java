package com.example.app.tutorialsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Tutorials APP", version = "1.0", description= "Tutorials storing app"))
public class TutorialsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialsAppApplication.class, args);
	}

}
