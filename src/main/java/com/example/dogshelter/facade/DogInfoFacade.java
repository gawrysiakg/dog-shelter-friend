package com.example.dogshelter.facade;

import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import com.example.dogshelter.service.DogInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DogInfoFacade {

    private final DogInfoService dogInfoService;

    public DogInfo getDogInfo(String breed){
        return dogInfoService.getDogInfo(breed);
    }

}
