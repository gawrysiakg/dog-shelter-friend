package com.example.dogshelter.api.dog_ninja;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DogNinjaConfig {


    @Value("${dog.ninja.name.apiEndpoint}")
    private String dogNinjaNameApiEndpoint;

    @Value("${dog.ninja.header.apiKey}")
    private String headerApiKey;

    @Value("${dog.ninja.header.apiHost}")
    private String headerApiHost;



}
