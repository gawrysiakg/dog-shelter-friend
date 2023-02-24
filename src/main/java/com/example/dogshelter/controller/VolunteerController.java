package com.example.dogshelter.controller;

import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.facade.VolunteerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/volunteers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VolunteerController {


    private final VolunteerFacade volunteerFacade;

    @GetMapping
    public ResponseEntity<List<VolunteerDto>> getVolunteers() {
        return ResponseEntity.ok(volunteerFacade.getAllVolunteers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VolunteerDto> getVolunteerById(@PathVariable Long id) throws VolunteerNotFoundException {
        return ResponseEntity.ok(volunteerFacade.getVolunteerById(id));
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<VolunteerDto> getVolunteerByLogin(@PathVariable String login) throws VolunteerNotFoundException {
        return ResponseEntity.ok(volunteerFacade.getVolunteerByLogin(login));
    }

    @GetMapping(value = "/email/{mail}")
    public ResponseEntity<VolunteerDto> getVolunteerByEmail(@PathVariable String mail) throws VolunteerNotFoundException {
        return ResponseEntity.ok(volunteerFacade.getVolunteerByEmail(mail));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addVolunteer(@RequestBody VolunteerDto volunteerDto) {
        volunteerFacade.addNewVolunteer(volunteerDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VolunteerDto> updateVolunteer(@RequestBody VolunteerDto volunteerDto) throws VolunteerNotFoundException {
        return ResponseEntity.ok(volunteerFacade.updateVolunteer(volunteerDto));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) throws VolunteerNotFoundException {
        volunteerFacade.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    }
}
