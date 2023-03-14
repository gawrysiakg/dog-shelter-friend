package com.example.dogshelter.service;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @InjectMocks
    private DogService dogService;
    @Mock
    private DogRepository dogRepository;


    @Test
    void shouldReturnEmptyList() {
        //Given & When
        List<Dog> dogs = dogService.getAllDogs();
        //Then
        assertNotNull(dogs);
        assertEquals(0, dogs.size());
    }

    @Test
    void shouldThrowException() {
        //Given & When
        //Then
        assertThrows(DogNotFoundException.class, () -> dogService.getDogById(1L));
    }

    @Test
    void shouldGetAllDogs() {
        //Given
        List<Dog> dogMock = List.of(new Dog(1L, "Remo", "Mixed", false),
                                    new Dog(2L, "Luna", "Mixed", true));
        when(dogRepository.findAll()).thenReturn(dogMock);
        //When
        List<Dog> dogs = dogService.getAllDogs();
        //Then
        assertNotNull(dogs);
        assertEquals(2, dogs.size());
        assertEquals("Remo", dogs.get(0).getName());
        assertEquals("Mixed", dogs.get(1).getDogBreed());
    }

    @Test
    void shouldGetAllDogsByBreed() {
        //Given
        List<Dog> dogMock = List.of(new Dog(1L, "Remo", "Mixed", false),
                new Dog(2L, "Luna", "Mixed", true));
        when(dogRepository.findAllByDogBreed("Mixed")).thenReturn(dogMock);
        //When
        List<Dog> dogs = dogService.getAllDogsByBreed("Mixed");
        //Then
        assertNotNull(dogs);
        assertEquals(2, dogs.size());
    }

    @Test
    void shouldGetDogById() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(13L, "Remo", "Mixed", false);
        when(dogRepository.findById(dogMock.getId())).thenReturn(Optional.of(dogMock));
        //When
        Dog dog = dogService.getDogById(dogMock.getId());
        //Then
        assertNotNull(dog);
        assertEquals("Remo", dog.getName());
    }

    @Test
    void shouldGetDogByName() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(13L, "Remo", "Mixed", false);
        when(dogRepository.findDogByName(dogMock.getName())).thenReturn(Optional.of(dogMock));
        //When
        Dog dog = dogService.getDogByName(dogMock.getName());
        //Then
        assertNotNull(dog);
        assertEquals("Remo", dog.getName());
    }

    @Test
    void shouldSaveDog(){
        //Given
        Dog dogMock = new Dog(13L, "Remo", "Labrador", false);
        when(dogRepository.save(dogMock)).thenReturn(dogMock);
        //When
        Dog saved = dogService.save(dogMock);
        //Then
        assertNotNull(saved);
        assertEquals("Remo", saved.getName());
        assertEquals("Labrador", saved.getDogBreed());
        assertFalse(saved.isInShelter());
    }

    @Test
    void shouldUpdateDog() throws DogNotFoundException {
        //Given
        DogDto dogDto = new DogDto(13L, "Remo", "Labrador", false);
        Dog dogMock = new Dog(13L, "Remo", "Labrador", false);
        when(dogRepository.findById(dogDto.getId())).thenReturn(Optional.of(dogMock));
        when(dogService.updateDog(dogDto)).thenReturn(dogMock);
        //When
        Dog updatedDog = dogService.updateDog(dogDto);
        //Then
        assertNotNull(updatedDog);
        assertEquals("Labrador", updatedDog.getDogBreed());
    }


    @Test
    void shouldDeleteDog() throws DogNotFoundException {
        //Given
        DogDto dogDto = new DogDto(13L, "Remo", "Labrador", false);
        Dog dogMock = new Dog(13L, "Remo", "Labrador", false);
        when(dogRepository.save(dogMock)).thenReturn(dogMock);
        when(dogRepository.findAll()).thenReturn(List.of());
        Dog savedDog = dogRepository.save(dogMock);
        //When
        dogService.deleteDog(savedDog.getId());
        List<Dog> dogs = dogRepository.findAll();
        //Then
        assertNotNull(dogs);
        assertEquals(List.of(), dogRepository.findAll());
    }

}