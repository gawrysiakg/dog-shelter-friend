package com.example.dogshelter.api.randomDogApi;

import com.example.dogshelter.domain.api.RandomDog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RandomDogClient {

    private String randomDogByBreed = "https://dog.ceo/api/breed/";
    private final RestTemplate restTemplate;

    public RandomDog getRandomDog(String breed){
       return restTemplate.getForObject(randomDogByBreed+breed+"/images/random", RandomDog.class);
    }
}
