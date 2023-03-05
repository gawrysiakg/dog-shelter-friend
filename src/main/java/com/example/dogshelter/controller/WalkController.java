package com.example.dogshelter.controller;

import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.facade.WalkFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/walks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WalkController {

    private final WalkFacade walkFacade;


    @GetMapping
    public ResponseEntity<List<WalkDto>> getWalks() {
        return ResponseEntity.ok(walkFacade.getAllWalks());
    }

    @GetMapping(value = "/planned")
    public ResponseEntity<List<WalkDto>> getPlannedWalks(){
        return ResponseEntity.ok(walkFacade.getAllPlannedWalks());
    }
    @GetMapping(value = "/planned/{username}")
    public ResponseEntity<List<WalkDto>> getPlannedWalksForVolunteer(@PathVariable String username){
        return ResponseEntity.ok(walkFacade.getPlannedWalksForVolunteer(username));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WalkDto> getWalk(@PathVariable Long id) throws WalkNotFoundException {
        return ResponseEntity.ok(walkFacade.getWalk(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> startNewWalk(@RequestBody WalkDto walkDto) {
        walkFacade.addNewWalk(walkDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalkDto> updateWalk(@RequestBody WalkDto walkDto) throws WalkNotFoundException, VolunteerNotFoundException, DogNotFoundException {
        return ResponseEntity.ok(walkFacade.updateWalk(walkDto));
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changeWalk(@RequestBody WalkDto walkDto) throws WalkNotFoundException, VolunteerNotFoundException, DogNotFoundException {
        walkFacade.changeWalk(walkDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteWalk(@PathVariable Long id) throws WalkNotFoundException {
        walkFacade.deleteWalk(id);
        return ResponseEntity.ok().build();
    }

}
