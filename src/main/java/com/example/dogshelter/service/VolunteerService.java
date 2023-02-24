package com.example.dogshelter.service;

import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() {
       return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) throws VolunteerNotFoundException {
        return volunteerRepository.findById(id).orElseThrow(VolunteerNotFoundException::new);
    }

    public Volunteer getVolunteerByLogin(String login) throws VolunteerNotFoundException {
        return volunteerRepository.findVolunteerByLogin(login).orElseThrow(VolunteerNotFoundException::new);
    }

    public Volunteer getVolunteerByEmail(String mail) throws VolunteerNotFoundException {
        return volunteerRepository.findVolunteerByEmail(mail).orElseThrow(VolunteerNotFoundException::new);
    }

    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(VolunteerDto volunteerDto) throws VolunteerNotFoundException {
        Volunteer volunteerFromRepo = volunteerRepository.findById(volunteerDto.getId()).orElseThrow(VolunteerNotFoundException::new);
        volunteerFromRepo.setLogin(volunteerDto.getLogin());
        volunteerFromRepo.setPassword(volunteerDto.getPassword());
        volunteerFromRepo.setFirstName(volunteerDto.getFirstName());
        volunteerFromRepo.setLastName(volunteerDto.getLastName());
        volunteerFromRepo.setPhone(volunteerDto.getPhone());
        volunteerFromRepo.setEmail(volunteerFromRepo.getEmail());
        return volunteerRepository.save(volunteerFromRepo);
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
