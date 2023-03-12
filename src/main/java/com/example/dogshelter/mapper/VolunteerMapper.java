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
        if(!(volunteerDto.getFirstName()==null)){
            volunteer.setFirstName(volunteerDto.getFirstName());
        }
        if(!(volunteerDto.getLastName()==null)){
            volunteer.setLastName(volunteerDto.getLastName());
        }
        if(!(volunteerDto.getName()==null)){
            volunteer.setName(volunteerDto.getName());
        }
        if(!(volunteerDto.getPassword()==null)){
            volunteer.setPassword(volunteerDto.getPassword());
        }
        if(!(volunteerDto.getRole()==null)){
            volunteer.setRole(volunteerDto.getRole());
        }
        if(!(volunteerDto.getEmail()==null)){
            volunteer.setEmail(volunteerDto.getEmail());
        }
        if(!(volunteerDto.getPhone()==0)){
            volunteer.setPhone(volunteerDto.getPhone());
        }
        return volunteer;
    }

    public List<VolunteerDto> mapToVolunteerDtoList(List<Volunteer> volunteerList){
        return volunteerList.stream().map(this::mapToVolunteerDto).collect(Collectors.toList());
    }

    public List<Volunteer> mapToVolunteerList(List<VolunteerDto> volunteerDto){
        return volunteerDto.stream().map(this::mapToVolunteer).collect(Collectors.toList());
    }

}
