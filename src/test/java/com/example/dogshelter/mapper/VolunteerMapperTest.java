package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Role;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolunteerMapperTest {

    @Autowired
    private VolunteerMapper volunteerMapper;


    @Test
    void shouldMapToVolunteerDto(){
        //Given
        Volunteer volunteer = new Volunteer(1L, "tom", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN);
        //when
        VolunteerDto volunteerDto = volunteerMapper.mapToVolunteerDto(volunteer);
        //Then
        assertEquals("tom", volunteerDto.getFirstName());
        assertEquals("Novak", volunteerDto.getLastName());
        assertEquals("tooom", volunteerDto.getName());
        assertEquals("toom01", volunteerDto.getPassword());
        assertEquals("tom@op.pl", volunteerDto.getEmail());
        assertEquals(554554554, volunteerDto.getPhone());
        assertEquals(Role.ADMIN, volunteerDto.getRole());
    }

    @Test
    void shouldMapToVolunteer(){
        //Given
        VolunteerDto volunteerDto = new VolunteerDto(1L, "tom", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN);
        //when
        Volunteer volunteer = volunteerMapper.mapToVolunteer(volunteerDto);
        //Then
        assertEquals("tom", volunteer.getFirstName());
        assertEquals("Novak", volunteer.getLastName());
        assertEquals("tooom", volunteer.getName());
        assertEquals("toom01", volunteer.getPassword());
        assertEquals("tom@op.pl", volunteer.getEmail());
        assertEquals(554554554, volunteer.getPhone());
        assertEquals(Role.ADMIN, volunteer.getRole());
    }

    @Test
    void shouldMapToVolunteerDtoList(){
        //Given
        List<Volunteer> volunteerList = List.of(
                new Volunteer(1L, "tom", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN),
                new Volunteer(2L, "sam", "Solton", "samtol", "sammt", "sam@op.pl", 448779654, Role.ADMIN)
        );
        //when
        List<VolunteerDto> volunteerDto = volunteerMapper.mapToVolunteerDtoList(volunteerList);
        //Then
        assertEquals("tom", volunteerDto.get(0).getFirstName());
        assertEquals("Novak", volunteerDto.get(0).getLastName());
        assertEquals("tooom", volunteerDto.get(0).getName());
        assertEquals("toom01", volunteerDto.get(0).getPassword());
        assertEquals("tom@op.pl", volunteerDto.get(0).getEmail());
        assertEquals(554554554, volunteerDto.get(0).getPhone());
        assertEquals(Role.ADMIN, volunteerDto.get(0).getRole());
        assertEquals("sam", volunteerDto.get(1).getFirstName());
        assertEquals("Solton", volunteerDto.get(1).getLastName());
    }

    @Test
    void shouldMapToVolunteerList(){
        //Given
        List<VolunteerDto> volunteerDtoList = List.of(
                new VolunteerDto(1L, "tom", "Novak", "tooom", "toom01", "tom@op.pl", 554554554, Role.ADMIN),
                new VolunteerDto(2L, "sam", "Solton", "samtol", "sammt", "sam@op.pl", 448779654, Role.ADMIN)
        );
        //when
        List<Volunteer> volunteer = volunteerMapper.mapToVolunteerList(volunteerDtoList);
        //Then
        assertEquals("tom", volunteer.get(0).getFirstName());
        assertEquals("Novak", volunteer.get(0).getLastName());
        assertEquals("tooom", volunteer.get(0).getName());
        assertEquals("toom01", volunteer.get(0).getPassword());
        assertEquals("tom@op.pl", volunteer.get(0).getEmail());
        assertEquals(554554554, volunteer.get(0).getPhone());
        assertEquals(Role.ADMIN, volunteer.get(0).getRole());
        assertEquals("sam", volunteer.get(1).getFirstName());
        assertEquals("Solton", volunteer.get(1).getLastName());
    }





}