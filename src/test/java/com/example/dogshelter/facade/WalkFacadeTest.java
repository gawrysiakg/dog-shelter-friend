package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Role;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.mapper.WalkMapper;
import com.example.dogshelter.service.WalkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalkFacadeTest {

    @InjectMocks
    private WalkFacade walkFacade;
    @Mock
    private WalkService walkService;
    @Mock
    private WalkMapper walkMapper;

    @Test
    void shouldGetAllWalks(){
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        List<Walk> walkMock = List.of(new Walk(1L, LocalDate.of(2023, 03, 10), volunteer,  dog),
                new Walk(2L, LocalDate.of(2023, 03, 12), volunteer,  dog));
        List<WalkDto> walkDtoMock = List.of(new WalkDto(1L, LocalDate.of(2023, 03, 10), volunteer.getName(),  dog.getName()),
                new WalkDto(2L, LocalDate.of(2023, 03, 12), volunteer.getName(),  dog.getName()));
        when(walkMapper.mapToWalkDtoList(walkMock)).thenReturn(walkDtoMock);
        when(walkService.findAllWalks()).thenReturn(walkMock);
        //When
        List<WalkDto> walks = walkFacade.getAllWalks();
        //Then
        assertNotNull(walks);
        assertEquals(2, walks.size());
        assertEquals( LocalDate.of(2023, 03, 10), walks.get(0).getWalkDate());
        assertEquals("ben", walks.get(1).getVolunteerName());
        assertEquals("Remo", walks.get(1).getDogName());
    }

    @Test
    void shouldGetPlannedWalksForVolunteer(){
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        List<Walk> walkMock = List.of(new Walk(1L, LocalDate.of(2023, 03, 10), volunteer,  dog),
                new Walk(2L, LocalDate.of(2023, 03, 12), volunteer,  dog));
        List<WalkDto> walkDtoMock = List.of(new WalkDto(1L, LocalDate.of(2023, 03, 10), volunteer.getName(),  dog.getName()),
                new WalkDto(2L, LocalDate.of(2023, 03, 12), volunteer.getName(),  dog.getName()));
        when(walkMapper.mapToWalkDtoList(walkMock)).thenReturn(walkDtoMock);
        when(walkService.getPlannedWalksForVolunteer(volunteer.getName())).thenReturn(walkMock);
        //When
        List<WalkDto> walks = walkFacade.getPlannedWalksForVolunteer("ben");
        //Then
        assertNotNull(walks);
        assertEquals(2, walks.size());
        assertEquals( LocalDate.of(2023, 03, 10), walks.get(0).getWalkDate());
        assertEquals("ben", walks.get(1).getVolunteerName());
        assertEquals("Remo", walks.get(1).getDogName());
    }
}