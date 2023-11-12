package com.nikhil.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
@OpenAPIDefinition(
		info = @Info(
		title= "Spring Boot REST API Documentation",
		description = "Spring Boot REST API Documentation",
		version = "v1.0",
		contact =@Contact(
				name="Nikhil",
				email="nik.k@gmail.com"
				),
		license =@License(
				name="Aapache 2.0"
				)),
		externalDocs = @ExternalDocumentation(
				description = "spring boot user management system",
				url= "https://www.google.com"
				)
		)
@SpringBootApplication
public class SpringbootRestfulWebservicesApplication {
    @Bean
    public ModelMapper modelMapper() {
	return new ModelMapper();
}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
