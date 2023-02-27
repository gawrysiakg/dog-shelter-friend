package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class DogMapper {

   // private final DogBreedMapper dogBreedMapper;
    public DogDto mapToDogDto(Dog dog) {
        return new DogDto(
                dog.getId(),
                dog.getName(),
                dog.getDogBreed(),
                dog.isInShelter());
    }

    public Dog mapToDog(DogDto dogDto) {
        Dog dog = new Dog();
        dog.setName(dogDto.getName());
        dog.setDogBreed(dogDto.getBreed());
        dog.setInShelter(dogDto.isInShelter());
        return dog;
    }

    public List<DogDto> mapToDogDtoList(List<Dog> dogList){
        return dogList.stream().map(this::mapToDogDto).collect(Collectors.toList());
    }

    public List<Dog> mapToDogList(List<DogDto> dogDtoList){
        return dogDtoList.stream().map(this::mapToDog).collect(Collectors.toList());
    }

}
