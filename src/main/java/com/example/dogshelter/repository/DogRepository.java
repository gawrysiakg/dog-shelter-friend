package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.DogBreed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
    @Override
    List<Dog> findAll();

    List<Dog> findAllByDogBreed(DogBreed breed);
}
