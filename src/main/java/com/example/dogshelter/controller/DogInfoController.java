package com.example.dogshelter.controller;

import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import com.example.dogshelter.facade.DogInfoFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/info")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DogInfoController {

    private final DogInfoFacade dogInfoFacade;


    @GetMapping(value = "/{breed}")
    public ResponseEntity<DogInfo> getDogInfo(@PathVariable String breed) {
        return ResponseEntity.ok(dogInfoFacade.getDogInfo(breed));
    }

}
