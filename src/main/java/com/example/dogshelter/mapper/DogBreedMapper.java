package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.DogBreed;
import com.example.dogshelter.dto.DogBreedDto;
import java.util.List;
import java.util.stream.Collectors;

public class DogBreedMapper {

    public DogBreedDto mapToDogBreedDto(DogBreed breed) {
        return new DogBreedDto(breed.toString());
    }

    public DogBreed mapToDogBreed(DogBreedDto dogBreedDto) {
        if (dogBreedDto.getBreed().equalsIgnoreCase("beagle")) {
            return DogBreed.BEAGLE;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("boxer")) {
            return DogBreed.BOXER;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("bull terrier")) {
            return DogBreed.BULL_TERRIER;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("doberman")) {
            return DogBreed.DOBERMAN;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("german shephard")) {
            return DogBreed.GERMAN_SHEPHERD;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("husky")) {
            return DogBreed.HUSKY;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("miniature pinscher")) {
            return DogBreed.MINIATURE_PINSCHER;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("labrador retriever")) {
            return DogBreed.LABRADOR_RETRIEVER;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("rottweiler")) {
            return DogBreed.ROTTWEILER;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("saint bernard")) {
            return DogBreed.SAINT_BERNARD;
        } else if (dogBreedDto.getBreed().equalsIgnoreCase("shih tzu")) {
            return DogBreed.SHIH_TZU;
        } else return DogBreed.MIXED;
    }

    public List<DogBreedDto> mapToDogBreedDtoList(List<DogBreed> dogList){
        return dogList.stream().map(this::mapToDogBreedDto).collect(Collectors.toList());
    }

    public List<DogBreed> mapToDogBreedList(List<DogBreedDto> dogDtoList){
        return dogDtoList.stream().map(this::mapToDogBreed).collect(Collectors.toList());
    }
}
