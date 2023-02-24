package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.VolunteerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class WalkMapper {

    @Autowired
    DogRepository dogRepository;
    @Autowired
    VolunteerRepository volunteerRepository;

    WalkDto mapToWalkDto(Walk walk) {
        return new WalkDto(
                walk.getId(),
                walk.getExitTime(),
                walk.getReturnTime(),
                walk.getDog().getId(),
                walk.getVolunteer().getId());
    }

    Walk mapToWalk(WalkDto walkDto) {
        Dog dog = dogRepository.findById(walkDto.getDogId()).get();
        Volunteer volunteer = volunteerRepository.findById(walkDto.getVolunteerId()).get();
        Walk walk = new Walk();
        walk.setExitTime(walkDto.getExitTime());
        walk.setReturnTime(walkDto.getReturnTime());
        walk.setDog(dog);
        walk.setVolunteer(volunteer);
        return walk;
    }

    List<WalkDto> mapToWalkDtoList(List<Walk> walkList){
        return walkList.stream().map(this::mapToWalkDto).collect(Collectors.toList());
    }

    List<Walk> mapToWalkList(List<WalkDto> walkDtoList){
        return walkDtoList.stream().map(this::mapToWalk).collect(Collectors.toList());
    }
}
