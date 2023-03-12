package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.VolunteerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class WalkMapper {

    private final DogRepository dogRepository;
    private final VolunteerRepository volunteerRepository;

    public WalkDto mapToWalkDto(Walk walk) {
        return new WalkDto(
                walk.getId(),
                walk.getWalkDate(),
                walk.getVolunteer().getName(),
                walk.getDog().getName());
    }

    public Walk mapToWalk(WalkDto walkDto)  {
        Walk walk = new Walk();
       try{
           Dog dog = dogRepository.findDogByName(walkDto.getDogName()).orElseThrow(DogNotFoundException::new);
           Volunteer volunteer = volunteerRepository.findVolunteerByName(walkDto.getVolunteerName()).orElseThrow(VolunteerNotFoundException::new);
           walk.setWalkDate(walkDto.getWalkDate());
           walk.setDog(dog);
           walk.setVolunteer(volunteer);
       } catch (DogNotFoundException| VolunteerNotFoundException exception){
                exception.printStackTrace();
        }

        return walk;
    }

    public List<WalkDto> mapToWalkDtoList(List<Walk> walkList){
        return walkList.stream().map(this::mapToWalkDto).collect(Collectors.toList());
    }

    public List<Walk> mapToWalkList(List<WalkDto> walkDtoList){
        return walkDtoList.stream().map(this::mapToWalk).collect(Collectors.toList());
    }
}
