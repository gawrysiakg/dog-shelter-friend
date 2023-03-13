package com.example.dogshelter.service;

import com.example.dogshelter.domain.Role;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VolunteerServiceTest {

    @InjectMocks
    private VolunteerService volunteerService;
    @Mock
    private VolunteerRepository volunteerRepository;

    @Test
    void shouldReturnEmptyList() {
        //Given & When
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        //Then
        assertNotNull(volunteers);
        assertEquals(0, volunteers.size());
    }

    @Test
    void shouldThrowException() {
        //Given & When
        //Then
        assertThrows(VolunteerNotFoundException.class, () -> volunteerService.getVolunteerById(1l));
    }

    @Test
    void shouldGetAllVolunteers() {
        //Given
        List<Volunteer> volunteerMock = List.of(new Volunteer(1L, "Adan", "Nowacky", "adam01", "adam0101","ad@am.pl", 44544544, Role.ADMIN),
                new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN));
        when(volunteerRepository.findAll()).thenReturn(volunteerMock);
        //When
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        //Then
        assertNotNull(volunteers);
        assertEquals(2, volunteers.size());
        assertEquals("adam01", volunteers.get(0).getName());
        assertEquals("Ben", volunteers.get(1).getFirstName());
    }

    @Test
    void shouldGetAllVolunteerById() throws VolunteerNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        when(volunteerRepository.findById(anyLong())).thenReturn(Optional.of(volunteer));
        //When
        Volunteer result = volunteerService.getVolunteerById(2L);
        //Then
        assertNotNull(result);
        assertEquals("Ben", result.getFirstName());
    }

    @Test
    void shouldGetAllVolunteerByUsername() throws VolunteerNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101", "ben@am.pl", 42342544, Role.ADMIN);
        when(volunteerRepository.findVolunteerByName(anyString())).thenReturn(Optional.of(volunteer));
        //When
        Volunteer result = volunteerService.getVolunteerByName("ben");
        //Then
        assertNotNull(result);
        assertEquals("Ben", result.getFirstName());
        assertEquals("ben", result.getName());
    }

    @Test
    void shouldGetAllVolunteerByEmail() throws VolunteerNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Ben", "Ten", "ben", "ben0101", "ben@am.pl", 42342544, Role.ADMIN);
        when(volunteerRepository.findVolunteerByEmail(anyString())).thenReturn(Optional.of(volunteer));
        //When
        Volunteer result = volunteerService.getVolunteerByEmail("ben@am.pl");
        //Then
        assertNotNull(result);
        assertEquals("Ben", result.getFirstName());
        assertEquals("ben", result.getName());
        assertEquals("ben@am.pl", result.getEmail());
    }

    @Test
    void shouldSaveVolunteer() {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        when(volunteerRepository.save(volunteer)).thenReturn(volunteer);
        //When
        Volunteer result = volunteerService.addVolunteer(volunteer);
        //Then
        assertNotNull(result);
        assertEquals("Alan", result.getFirstName());
        assertEquals("alan@mail.pl", result.getEmail());
    }

    @Test
    void shouldUpdateVolunteer() throws VolunteerNotFoundException {
        //Given
        VolunteerDto volunteerDto = new VolunteerDto(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        Volunteer volunteerMock = new Volunteer(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        when(volunteerRepository.findById(volunteerDto.getId())).thenReturn(Optional.of(volunteerMock));
        when(volunteerService.updateVolunteer(volunteerDto)).thenReturn(volunteerMock);
        //When
        Volunteer result = volunteerService.updateVolunteer(volunteerDto);
        //Then
        assertNotNull(result);
        assertEquals("alan0101", result.getPassword());
    }

    @Test
    void shouldDeleteVolunteer()  {
        //Given
        VolunteerDto volunteerDto = new VolunteerDto(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        Volunteer volunteerMock = new Volunteer(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        when(volunteerRepository.save(volunteerMock)).thenReturn(volunteerMock);
        when(volunteerRepository.findAll()).thenReturn(List.of());
        Volunteer savedVolunteer = volunteerRepository.save(volunteerMock);
        //When
        volunteerService.deleteVolunteer(volunteerDto.getId());
        List<Volunteer> result = volunteerService.getAllVolunteers();
        //Then
        assertNotNull(result);
        assertEquals(List.of(), volunteerService.getAllVolunteers());
    }
}