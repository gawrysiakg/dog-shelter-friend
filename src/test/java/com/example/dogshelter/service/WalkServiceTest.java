package com.example.dogshelter.service;

import com.example.dogshelter.config.AdminConfig;
import com.example.dogshelter.domain.*;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.repository.VolunteerRepository;
import com.example.dogshelter.repository.WalkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalkServiceTest {

    @InjectMocks
    private WalkService walkService;
    @Mock
    private WalkRepository walkRepository;
    @Mock
    private VolunteerRepository volunteerRepository;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private AdminConfig adminConfig;


    @Test
    void shouldReturnEmptyList() {
        //Given & When
        List<Walk> walks = walkService.findAllRunningWalks();
        //Then
        assertNotNull(walks);
        assertEquals(0, walks.size());
    }

    @Test
    void shouldThrowException() {
        //Given & When
        //Then
        assertThrows(WalkNotFoundException.class, () -> walkService.findWalk(1l));
    }

    @Test
    void shouldGetAllWalks() {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        List<Walk> walkMock = List.of(new Walk(1L, LocalDate.of(2023, 03, 10), volunteer,  dog),
                                      new Walk(2L, LocalDate.of(2023, 03, 12), volunteer,  dog));
        when(walkRepository.findAll()).thenReturn(walkMock);
        //When
        List<Walk> walks = walkService.findAllWalks();
        //Then
        assertNotNull(walks);
        assertEquals(2, walks.size());
        assertEquals( LocalDate.of(2023, 03, 10), walks.get(0).getWalkDate());
        assertEquals("Ben", walks.get(1).getVolunteer().getFirstName());
        assertEquals("Ten", walks.get(1).getVolunteer().getLastName());
    }

    @Test
    void shouldFindAllPlannedWalks() {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        List<Walk> walkMock = List.of(new Walk(1L, LocalDate.of(2023, 03, 10), volunteer,  dog),
                new Walk(2L, LocalDate.of(2023, 03, 12), volunteer,  dog));
        when(walkRepository.findAllByWalkDateIsAfter(any(LocalDate.class))).thenReturn(walkMock);
        //When
        List<Walk> walks = walkService.findAllPlannedWalks();
        //Then
        assertNotNull(walks);
        assertEquals(2, walks.size());
        assertEquals( LocalDate.of(2023, 03, 10), walks.get(0).getWalkDate());
        assertEquals("Ben", walks.get(1).getVolunteer().getFirstName());
        assertEquals("Ten", walks.get(1).getVolunteer().getLastName());
    }

    @Test
    void shouldGetPlannedWalkListForVolunteer()  {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        List<Walk> walkMock = List.of(new Walk(1L, LocalDate.of(2023, 03, 10), volunteer,  dog),
                new Walk(2L, LocalDate.of(2023, 03, 12), volunteer,  dog));
        when(walkRepository.findAllByVolunteerNameAndWalkDateIsAfter("ben",  LocalDate.now().minusDays(1) )).thenReturn(walkMock);
        //When
        List<Walk> result = walkService.getPlannedWalksForVolunteer("ben");
        //Then
        assertNotNull(result);
        assertEquals("Ben", result.get(0).getVolunteer().getFirstName());
        assertEquals(2, result.size());
    }


    @Test
    void shouldCreateWalk() throws WalkNotFoundException, VolunteerNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101", "ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        Walk walk = new Walk(1L, LocalDate.of(2023, 03, 10), volunteer, dog);
        when(walkRepository.save(walk)).thenReturn(walk);
        doNothing().when(simpleEmailService).send(any(Mail.class));
        when(volunteerRepository.findById(anyLong())).thenReturn(Optional.of(volunteer));
        when(walkRepository.findById(anyLong())).thenReturn(Optional.of(walk));
        when(adminConfig.getAdminMail()).thenReturn(anyString());
        //When
        walkService.addNewWalk(walk);
        Walk walkFromRepo = walkService.findWalk(walk.getId());
        //Then
        assertNotNull(walkFromRepo);
        assertEquals("Ben", walkFromRepo.getVolunteer().getFirstName());
        assertEquals(2L, walkFromRepo.getVolunteer().getId());
        assertEquals(13L, walkFromRepo.getDog().getId());
        //CleanUp
        walkService.deleteWalk(walk.getId());
    }


    @Test
    void shouldDeleteWalk() throws WalkNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101", "ben@am.pl", 42342544, Role.ADMIN);
        Dog dog = new Dog(13L, "Remo", "Labrador", false);
        Walk walk = new Walk(1L, LocalDate.of(2023, 03, 10), volunteer, dog);
        when(walkRepository.save(walk)).thenReturn(walk);
        when(walkRepository.findAll()).thenReturn(List.of());
        Walk savedWalk = walkRepository.save(walk);
        //When
        walkService.deleteWalk(savedWalk.getId());
        List<Walk> walks = walkRepository.findAll();
        //Then
        assertNotNull(walks);
        assertEquals(List.of(), walks);
    }
}