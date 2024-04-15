package com.example.gymfitnew;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Gym Fit microservice REST API Documentation",
				description = "Gy microservice REST API Documentation",
				version = "v1"
				)
)
public class GymfitnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymfitnewApplication.class, args);
	}
}
