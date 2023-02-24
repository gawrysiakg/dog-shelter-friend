package com.example.dogshelter.controller;

import com.example.dogshelter.domain.DogBreed;
import com.example.dogshelter.dto.DogBreedDto;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.facade.DogFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/dogs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DogController {

    private final DogFacade dogFacade;

    @GetMapping
    public ResponseEntity<List<DogDto>> getDogs() {
        return ResponseEntity.ok(dogFacade.getAllDogs());
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DogDto>> getDogsByBreed(@RequestBody DogBreedDto breed){
        return ResponseEntity.ok(dogFacade.getAllDogsByBreed(breed));
    }

    @GetMapping(value = "{dogId}")
    public ResponseEntity<DogDto> getDog(@PathVariable Long dogId) throws DogNotFoundException {
        return ResponseEntity.ok(dogFacade.getDog(dogId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addDog(@RequestBody DogDto dogDto) {
        dogFacade.addNewDog(dogDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DogDto> updateDog(@RequestBody DogDto dogDto) throws DogNotFoundException {
        return ResponseEntity.ok(dogFacade.updateDog(dogDto));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) throws DogNotFoundException {
        dogFacade.deleteDog(id);
        return ResponseEntity.ok().build();
    }
}
