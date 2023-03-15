package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.mapper.DogMapper;
import com.example.dogshelter.service.DogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogFacadeTest {

    @InjectMocks
    private DogFacade dogFacade;
    @Mock
    private DogService dogService;
    @Mock
    private DogMapper dogMapper;


    @Test
    void shouldGetAllDogs(){
        //Given
        List<Dog> dogMock = List.of(new Dog(1L, "Remo", "Mixed", false),
                new Dog(2L, "Luna", "Mixed", true));
        List<DogDto> dogDtoMock = List.of(new DogDto(1L, "Remo", "Mixed", false),
                new DogDto(2L, "Luna", "Mixed", true));
        when(dogService.getAllDogs()).thenReturn(dogMock);
        when(dogMapper.mapToDogDtoList(anyList())).thenReturn(dogDtoMock);
        //When
        List<DogDto> dogs = dogFacade.getAllDogs();
        //Then
        assertNotNull(dogs);
        assertEquals(2, dogs.size());
        assertEquals("Remo", dogs.get(0).getName());
        assertEquals("Mixed", dogs.get(1).getBreed());
    }

    @Test
    void shouldGetAllDogsByBreed(){
        //Given
        List<Dog> dogMock = List.of(new Dog(1L, "Remo", "Mixed", false),
                new Dog(2L, "Luna", "Mixed", true));
        List<DogDto> dogDtoMock = List.of(new DogDto(1L, "Remo", "Mixed", false),
                new DogDto(2L, "Luna", "Mixed", true));
        when(dogService.getAllDogsByBreed(anyString())).thenReturn(dogMock);
        when(dogMapper.mapToDogDtoList(anyList())).thenReturn(dogDtoMock);
        //When
        List<DogDto> dogs = dogFacade.getAllDogsByBreed("Mixed");
        //Then
        assertNotNull(dogs);
        assertEquals(2, dogs.size());
        assertEquals("Remo", dogs.get(0).getName());
        assertEquals("Mixed", dogs.get(1).getBreed());
    }

    @Test
    void shouldGetDogByName() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(1L, "Remo", "Mixed", false);
        DogDto dogDtoMock = new DogDto(1L, "Remo", "Mixed", false);
        when(dogService.getDogByName(anyString())).thenReturn(dogMock);
        when(dogMapper.mapToDogDto(any(Dog.class))).thenReturn(dogDtoMock);
        //When
        DogDto dogs = dogFacade.getDogByName("Remo");
        //Then
        assertNotNull(dogs);
        assertEquals("Remo", dogs.getName());
        assertEquals("Mixed", dogs.getBreed());
    }

    @Test
    void shouldGetDogById() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(1L, "Remo", "Mixed", false);
        DogDto dogDtoMock = new DogDto(1L, "Remo", "Mixed", false);
        when(dogService.getDogById(anyLong())).thenReturn(dogMock);
        when(dogMapper.mapToDogDto(any(Dog.class))).thenReturn(dogDtoMock);
        //When
        DogDto dogs = dogFacade.getDogById(1L);
        //Then
        assertNotNull(dogs);
        assertEquals("Remo", dogs.getName());
        assertEquals("Mixed", dogs.getBreed());
    }

    @Test
    void shouldAddNewDog() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(1L, "Remo", "Mixed", false);
        DogDto dogDtoMock = new DogDto(1L, "Remo", "Mixed", false);
        when(dogMapper.mapToDog(any(DogDto.class))).thenReturn(dogMock);
        when(dogService.save(any(Dog.class))).thenReturn(dogMock);
        when(dogService.getDogById(anyLong())).thenReturn(dogMock);
        when(dogFacade.getDogById(anyLong())).thenReturn(dogDtoMock);
        //When
        dogFacade.addNewDog(dogDtoMock);
        DogDto dog = dogFacade.getDogById(dogMock.getId());
        //Then
        assertNotNull(dog);
        assertEquals("Remo", dog.getName());
        assertEquals("Mixed", dog.getBreed());
    }

    @Test
    void shouldDeleteDog() throws DogNotFoundException {
        //Given
        Dog dogMock = new Dog(1L, "Remo", "Mixed", false);
        DogDto dogDtoMock = new DogDto(1L, "Remo", "Mixed", false);
        when(dogService.save(dogMock)).thenReturn(dogMock);
        when(dogService.getAllDogs()).thenReturn(List.of());
        when(dogMapper.mapToDog(dogDtoMock)).thenReturn(dogMock);
        dogFacade.addNewDog(dogDtoMock);
        //When
        dogFacade.deleteDog(dogDtoMock.getId());
        List<Dog> dogs = dogService.getAllDogs();
        //Then
        assertEquals(dogs.size(), 0);
    }

}