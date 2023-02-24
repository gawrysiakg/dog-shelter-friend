package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.DogBreed;
import com.example.dogshelter.dto.DogBreedDto;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.mapper.DogBreedMapper;
import com.example.dogshelter.mapper.DogMapper;
import com.example.dogshelter.service.DogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DogFacade {

    private final DogService dogService;
    private final DogMapper dogMapper;
    private final DogBreedMapper dogBreedMapper;


    public List<DogDto> getAllDogs(){
        List<Dog> dogs = dogService.getAllDogs();
        return  dogMapper.mapToDogDtoList(dogs);
    }

    public List<DogDto> getAllDogsByBreed(DogBreedDto breed){
        List<Dog> dogs = dogService.getAllDogsByBreed(dogBreedMapper.mapToDogBreed(breed));
        return  dogMapper.mapToDogDtoList(dogs);
    }

    public DogDto getDog(Long id) throws DogNotFoundException {
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
