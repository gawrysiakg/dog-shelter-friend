package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.mapper.DogMapper;
import com.example.dogshelter.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DogFacade {

    private final DogService dogService;
    private final DogMapper dogMapper;


    public List<DogDto> getAllDogs(){
        List<Dog> dogs = dogService.getAllDogs();
        return  dogMapper.mapToDogDtoList(dogs);
    }

    public List<DogDto> getAllDogsByBreed(String breed){
        List<Dog> dogs = dogService.getAllDogsByBreed(breed);
        return  dogMapper.mapToDogDtoList(dogs);
    }

    public DogDto getDogByName(String name) throws DogNotFoundException {
        return dogMapper.mapToDogDto( dogService.getDogByName(name));
    }

    public DogDto getDogById(Long id) throws DogNotFoundException {
            return dogMapper.mapToDogDto( dogService.getDogById(id));
        }

    public void addNewDog(DogDto dogDto) {
        Dog dog = dogMapper.mapToDog(dogDto);
        dogService.save(dog);
    }

    public DogDto updateDog(DogDto dogDto) throws DogNotFoundException {
        Dog dog = dogService.updateDog(dogDto);
        return dogMapper.mapToDogDto(dog);
    }

    public void deleteDog(Long id) throws DogNotFoundException {
            dogService.deleteDog(id);
    }
}
