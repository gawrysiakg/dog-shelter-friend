package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class DogMapper {

    public DogDto mapToDogDto(Dog dog) {
        return new DogDto(
                dog.getId(),
                dog.getName(),
                dog.getDogBreed(),
                dog.getDogStatus());
    }

    public Dog mapToDog(DogDto dogDto) {
        Dog dog = new Dog();
        dog.setName(dog.getName());
        dog.setDogBreed(dogDto.getDogBreed());
        dog.setDogStatus(dogDto.getDogStatus());
        return dog;
    }

    public List<DogDto> mapToDogDtoList(List<Dog> dogList){
        return dogList.stream().map(this::mapToDogDto).collect(Collectors.toList());
    }

    public List<Dog> mapToDogList(List<DogDto> dogDtoList){
        return dogDtoList.stream().map(this::mapToDog).collect(Collectors.toList());
    }

}
