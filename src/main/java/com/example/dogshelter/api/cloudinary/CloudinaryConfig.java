package com.example.dogshelter.api.cloudinary;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CloudinaryConfig {

    @Value("${cloudinary.cloudNameValue}")
    private String cloudNameValue;

    @Value("${cloudinary.apiKeyValue}")
    private String apiKeyValue;

    @Value("${cloudinary.apiSecretValue}")
    private String apiSecretValue;

}
