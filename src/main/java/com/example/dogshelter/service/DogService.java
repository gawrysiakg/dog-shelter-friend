package com.example.dogshelter.service;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.DogBreed;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;


    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public List<Dog> getAllDogsByBreed(DogBreed breed) {
        return dogRepository.findAllByDogBreed(breed);
    }

    public Dog getDogById(final Long id) throws DogNotFoundException {
        return dogRepository.findById(id).orElseThrow(DogNotFoundException::new);
    }

    public Dog save(final Dog dog){
        return dogRepository.save(dog);
    }

    public Dog updateDog(final DogDto dogDto) throws DogNotFoundException {
        Dog dog = dogRepository.findById(dogDto.getId()).orElseThrow(DogNotFoundException::new);
        dog.setDogStatus(dogDto.getDogStatus());
        dog.setDogBreed(dogDto.getDogBreed());
        dog.setName(dogDto.getName());
        return dogRepository.save(dog);
    }

    public void deleteDog(final Long id) throws DogNotFoundException {
        try {
            dogRepository.deleteById(id);
        } catch (Exception e) {
            throw new DogNotFoundException();
        }
    }


}
