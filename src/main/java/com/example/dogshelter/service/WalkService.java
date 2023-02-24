package com.example.dogshelter.service;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.dto.WalkFinishDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.VolunteerRepository;
import com.example.dogshelter.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final VolunteerRepository volunteerRepository;
    private final DogRepository dogRepository;

    public List<Walk> findAllWalks() {
        return walkRepository.findAll();
    }

    public List<Walk> findAllRunningWalks() {
        return walkRepository.findAllByReturnTimeIsNull();
    }

    public Walk findWalk(Long id) throws WalkNotFoundException {
        return walkRepository.findById(id).orElseThrow(WalkNotFoundException::new);
    }

    public void addNewWalk(Walk walk) {
        walkRepository.save(walk);
    }

    public Walk updatedWalk(WalkDto walkDto) throws WalkNotFoundException, VolunteerNotFoundException, DogNotFoundException {
        Walk walkFromRepo = walkRepository.findById(walkDto.getId()).orElseThrow(WalkNotFoundException::new);
        Volunteer volunteerFromRepo = volunteerRepository.findById(walkDto.getVolunteerId()).orElseThrow(VolunteerNotFoundException::new);
        Dog dogFromRepo = dogRepository.findById(walkDto.getDogId()).orElseThrow(DogNotFoundException::new);
        walkFromRepo.setVolunteer(volunteerFromRepo);
        walkFromRepo.setDog(dogFromRepo);
        walkFromRepo.setExitTime(walkDto.getExitTime());
        walkFromRepo.setReturnTime(walkFromRepo.getReturnTime());
        return walkRepository.save(walkFromRepo);
    }

    public void deleteWalk(Long id) throws WalkNotFoundException {
        try {
            walkRepository.deleteById(id);
        } catch ( Exception e) {
            throw new WalkNotFoundException();
        }
    }

    public void finishWalk(WalkFinishDto walkFinishDto) throws WalkNotFoundException {
        Walk walk = walkRepository.findById(walkFinishDto.getWalkId()).orElseThrow(WalkNotFoundException::new);
        walk.setReturnTime(walkFinishDto.getReturnTime());
        walkRepository.save(walk);
    }
}
