package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class VolunteerMapper {

    public VolunteerDto mapToVolunteerDto(Volunteer volunteer) {
        VolunteerDto volunteerDto = new VolunteerDto(
                volunteer.getId(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getName(),
                volunteer.getPassword(),
                volunteer.getEmail(),
                volunteer.getPhone(),
                volunteer.getRole());
        return volunteerDto;

    }

    public Volunteer mapToVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setFirstName(volunteerDto.getFirstName());
        volunteer.setLastName(volunteerDto.getLastName());
        volunteer.setName(volunteerDto.getName());
        volunteer.setPassword(volunteerDto.getPassword());
        volunteer.setRole(volunteerDto.getRole());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setPhone(volunteerDto.getPhone());
        return volunteer;
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList){
        return volunteerList.stream().map(this::mapToVolunteerDto).collect(Collectors.toList());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDto){
        return volunteerDto.stream().map(this::mapToVolunteer).collect(Collectors.toList());
    }

}
