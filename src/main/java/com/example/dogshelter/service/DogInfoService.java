package com.example.dogshelter.service;

import com.example.dogshelter.api.dog_ninja.DogNinjaClient;
import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogInfoService {

    private final DogNinjaClient dogNinjaClient;

    public DogInfo getDogInfo(String breed){
        return dogNinjaClient.getDogInfo(breed);
    }
}
