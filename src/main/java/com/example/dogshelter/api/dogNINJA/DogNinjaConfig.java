package com.example.dogshelter.api.dogNINJA;

import org.springframework.beans.factory.annotation.Value;

public class DogNinjaConfig {


    @Value("${cloudinary.cloudNameValue}")
    private String cloudNameValue;

    @Value("${cloudinary.apiKeyValue}")
    private String apiKeyValue;

    @Value("${cloudinary.apiSecretValue}")
    private String apiSecretValue;



}
