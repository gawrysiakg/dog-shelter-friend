package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Role;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.mapper.VolunteerMapper;
import com.example.dogshelter.service.VolunteerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolunteerFacadeTest {

    @InjectMocks
    private VolunteerFacade volunteerFacade;
    @Mock
    private VolunteerService volunteerService;
    @Mock
    private VolunteerMapper volunteerMapper;


    @Test
    void shouldGetAllVolunteers(){
        //Given
        List<Volunteer> volunteerMock = List.of(new Volunteer(1L, "Adan", "Nowacky", "adam01", "adam0101","ad@am.pl", 44544544, Role.ADMIN),
                new Volunteer(2L, "Ben", "Ten", "volunteers", "ben0101","ben@am.pl", 42342544, Role.ADMIN));
        List<VolunteerDto> volunteerDtoMock = List.of(new VolunteerDto(1L, "Adan", "Nowacky", "adam01", "adam0101","ad@am.pl", 44544544, Role.ADMIN),
                new VolunteerDto(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN));
        when(volunteerService.getAllVolunteers()).thenReturn(volunteerMock);
        when(volunteerMapper.mapToVolunteerDtoList(anyList())).thenReturn(volunteerDtoMock);
        //When
        List<VolunteerDto> volunteers = volunteerFacade.getAllVolunteers();
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
        VolunteerDto volunteerDto = new VolunteerDto(2L, "Ben", "Ten", "ben", "ben0101","ben@am.pl", 42342544, Role.ADMIN);
        when(volunteerMapper.mapToVolunteerDto(volunteer)).thenReturn(volunteerDto);
        when(volunteerService.getVolunteerById(anyLong())).thenReturn(volunteer);
        //When
        VolunteerDto result = volunteerFacade.getVolunteerById(2L);
        //Then
        assertNotNull(result);
        assertEquals("Ben", result.getFirstName());
    }

    @Test
    void shouldSaveVolunteer() throws VolunteerNotFoundException {
        //Given
        Volunteer volunteer = new Volunteer(2L, "Alan", "Smart", "alan", "alan0101", "alan@mail.pl", 888777555, Role.ADMIN);
        VolunteerDto volunteerDto = new VolunteerDto(2L, "Alan", "Smart", "alan", "alan0101","alan@mail.pl", 888777555, Role.ADMIN);
        when(volunteerMapper.mapToVolunteerDto(volunteer)).thenReturn(volunteerDto);
        when(volunteerMapper.mapToVolunteer(volunteerDto)).thenReturn(volunteer);
        when(volunteerService.getVolunteerById(anyLong())).thenReturn(volunteer);
        when(volunteerService.addVolunteer(volunteer)).thenReturn(volunteer);
        //When
        volunteerFacade.addNewVolunteer(volunteerDto);
        VolunteerDto result = volunteerFacade.getVolunteerById(volunteerDto.getId());
        //Then
        assertNotNull(result);
        assertEquals("Alan", result.getFirstName());
        assertEquals("alan@mail.pl", result.getEmail());
    }

}