package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.dto.DogBreedDto;
import com.example.dogshelter.dto.DogDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DogMapperTest {

    @Autowired
    private DogMapper dogMapper;

    @Test
    void shouldMapToDog(){
        //Given
        DogBreedDto dogBreedDto = new DogBreedDto("Labrador");
        DogDto dogDto = new DogDto(1L, "Ares", dogBreedDto.getBreed(), true);
        //When
        Dog dog = dogMapper.mapToDog(dogDto);
        //Then
        assertEquals("Ares", dog.getName());
        assertEquals("Labrador", dog.getDogBreed());
        assertTrue(dog.isInShelter());
    }

    @Test
    void shouldMapToDogDto(){
        //Given
        Dog dog = new Dog(1L, "Ares", "Labrador", true);
        //When
        DogDto dogDto = dogMapper.mapToDogDto(dog);
        //Then
        assertEquals(1L, dogDto.getId());
        assertEquals("Ares", dogDto.getName());
        assertEquals("Labrador", dogDto.getBreed());
        assertTrue(dogDto.isInShelter());
    }

    @Test
    void shouldMapToDogList(){
        //Given
        List<DogDto> dtoList = List.of(
                new DogDto(1L, "Ares", "Labrador", true),
                new DogDto(2L, "Bruno", "Mixed", false));
        //When
        List<Dog> dogList = dogMapper.mapToDogList(dtoList);
        //Then
        assertEquals("Ares", dogList.get(0).getName());
        assertEquals("Labrador", dogList.get(0).getDogBreed());
        assertTrue(dogList.get(0).isInShelter());
        assertEquals("Bruno", dogList.get(1).getName());
        assertEquals("Mixed", dogList.get(1).getDogBreed());
        assertFalse(dogList.get(1).isInShelter());
    }

    @Test
    void shouldMapToDogDtoList(){
        //Given
        List<Dog> list = List.of(
                new Dog(1L, "Ares", "Labrador", true),
                new Dog(2L, "Bruno", "Mixed", false));
        //When
        List<DogDto> dogDtoList = dogMapper.mapToDogDtoList(list);
        //Then
        assertEquals(1L, dogDtoList.get(0).getId());
        assertEquals("Ares", dogDtoList.get(0).getName());
        assertEquals("Labrador", dogDtoList.get(0).getBreed());
        assertTrue(dogDtoList.get(0).isInShelter());
        assertEquals(2L, dogDtoList.get(1).getId());
        assertEquals("Bruno", dogDtoList.get(1).getName());
        assertEquals("Mixed", dogDtoList.get(1).getBreed());
        assertFalse(dogDtoList.get(1).isInShelter());
    }
}