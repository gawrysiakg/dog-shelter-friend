package com.example.dogshelter.facade;

import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.mapper.VolunteerMapper;
import com.example.dogshelter.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VolunteerFacade {

    private final VolunteerService volunteerService;
    private final VolunteerMapper volunteerMapper;

    public List<VolunteerDto> getAllVolunteers() {
        return volunteerMapper.mapToVolunteerDtoList(volunteerService.getAllVolunteers());
    }

    public VolunteerDto getVolunteerById(Long id) throws VolunteerNotFoundException {
        return volunteerMapper.mapToVolunteerDto(volunteerService.getVolunteerById(id));
    }

    public VolunteerDto getVolunteerByName(String login) throws VolunteerNotFoundException {
        return volunteerMapper.mapToVolunteerDto(volunteerService.getVolunteerByName(login));
    }

    public VolunteerDto getVolunteerByEmail(String mail) throws VolunteerNotFoundException {
        return volunteerMapper.mapToVolunteerDto(volunteerService.getVolunteerByEmail(mail));
    }

    public void addNewVolunteer(VolunteerDto volunteerDto) {
       volunteerService.addVolunteer(volunteerMapper.mapToVolunteer(volunteerDto));
    }

    public VolunteerDto updateVolunteer(VolunteerDto volunteerDto) throws VolunteerNotFoundException {
        Volunteer volunteer = volunteerService.updateVolunteer(volunteerDto);
        return volunteerMapper.mapToVolunteerDto(volunteer);
    }

    public void deleteVolunteer(Long id) {
        volunteerService.deleteVolunteer(id);
    }
}
