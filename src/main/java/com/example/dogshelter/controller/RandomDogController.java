package com.example.dogshelter.controller;

import com.example.dogshelter.api.randomDogApi.RandomDogClient;
import com.example.dogshelter.domain.api.RandomDog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/randomdog")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RandomDogController {

    private final RandomDogClient randomDogClient;

    @GetMapping
    public ResponseEntity<RandomDog> getRandomDogByBreed(String breed) {
        return ResponseEntity.ok(randomDogClient.getRandomDog(breed));
    }
}
