package com.example.dogshelter.facade;

import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.dto.WalkFinishDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.mapper.WalkMapper;
import com.example.dogshelter.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WalkFacade {

    private final WalkService walkService;
    private final WalkMapper walkMapper;


    public List<WalkDto> getAllWalks() {
        return walkMapper.mapToWalkDtoList(walkService.findAllWalks());
    }

    public List<WalkDto> getAllUnfinishedWalks() {
        return walkMapper.mapToWalkDtoList(walkService.findAllRunningWalks());
    }

    public WalkDto getWalk(Long id) throws WalkNotFoundException {
        return walkMapper.mapToWalkDto(walkService.findWalk(id));
    }

    public void addNewWalk(WalkDto walkDto) {
        walkService.addNewWalk(walkMapper.mapToWalk(walkDto));
    }

    public WalkDto updateWalk(WalkDto walkDto) throws WalkNotFoundException, VolunteerNotFoundException, DogNotFoundException {
         return walkMapper.mapToWalkDto(walkService.updatedWalk(walkDto));
    }

    public void deleteWalk(Long id) throws WalkNotFoundException {
        walkService.deleteWalk(id);
    }

    public void finishWalk(WalkFinishDto walkFinishDto) throws WalkNotFoundException {
        walkService.finishWalk(walkFinishDto);
    }
}
