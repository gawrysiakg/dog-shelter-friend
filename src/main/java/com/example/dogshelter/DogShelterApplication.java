package com.example.dogshelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition
@SpringBootApplication
public class DogShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogShelterApplication.class, args);
    }


}
