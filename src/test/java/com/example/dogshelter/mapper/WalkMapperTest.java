package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Role;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.WalkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkMapperTest {

    @Autowired
    private WalkMapper walkMapper;

    @Test
    void shouldMapToWalkDto(){
        //Given
        Volunteer volunteer = new Volunteer(1L, "Adam", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN);
        Dog dog = new Dog(1L, "Ares", "Labrador", true);
        Walk walk = new Walk(1L, LocalDate.of(2023, 03, 10), volunteer, dog);
        //When
        WalkDto walkDto = walkMapper.mapToWalkDto(walk);
        //Then
        assertEquals(LocalDate.of(2023, 03, 10), walkDto.getWalkDate());
        assertEquals("tooom", walkDto.getVolunteerName());
        assertEquals("Ares", walkDto.getDogName());
    }

    @Test
    void shouldMapToWalkDtoList() {
        //Given
        List<Walk> walks = new ArrayList<>();
        Volunteer volunteer = new Volunteer(1L, "Adam", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN);
        Dog dog = new Dog(1L, "Ares", "Labrador", true);
        Walk walk = new Walk(1L, LocalDate.of(2023, 03, 10), volunteer, dog);
        walks.add(walk);
        //When
        List<WalkDto> walksDto = walkMapper.mapToWalkDtoList(walks);
        //Then
        assertEquals(1, walksDto.size());
        assertEquals("Ares", walksDto.get(0).getDogName());
        assertEquals("tooom", walksDto.get(0).getVolunteerName());
    }
}