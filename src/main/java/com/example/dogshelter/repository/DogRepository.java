package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
    @Override
    List<Dog> findAll();

    List<Dog> findAllByDogBreed(String breed);

    Optional<Dog> findDogByName(String name);
}
