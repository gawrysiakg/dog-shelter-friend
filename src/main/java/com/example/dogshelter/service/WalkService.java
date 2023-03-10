package com.example.dogshelter.service;

import com.example.dogshelter.config.AdminConfig;
import com.example.dogshelter.domain.Dog;
import com.example.dogshelter.domain.Mail;
import com.example.dogshelter.domain.Volunteer;
import com.example.dogshelter.domain.Walk;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.dto.WalkFinishDto;
import com.example.dogshelter.exception.DogNotFoundException;
import com.example.dogshelter.exception.VolunteerNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.repository.DogRepository;
import com.example.dogshelter.repository.VolunteerRepository;
import com.example.dogshelter.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final VolunteerRepository volunteerRepository;
    private final DogRepository dogRepository;
    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;

    public static final String SUBJECT = "New walk for volunteer";

    public List<Walk> findAllWalks() {
        return walkRepository.findAll();
    }

    public List<Walk> findAllPlannedWalks(){
        return walkRepository.findAllByWalkDateIsAfter(LocalDate.now().minusDays(1));
    }

    public List<Walk> getPlannedWalksForVolunteer(String username){
        return walkRepository.findAllByVolunteerNameAndWalkDateIsAfter(username, LocalDate.now().minusDays(1));
    }

    public Walk findWalk(Long id) throws WalkNotFoundException {
        return walkRepository.findById(id).orElseThrow(WalkNotFoundException::new);
    }

    public void addNewWalk(Walk walk) throws WalkNotFoundException, VolunteerNotFoundException {
        Walk walkSaved = walkRepository.save(walk);
        sendConfirmToVolunteer(walkSaved);
        sendConfirmToAdmin(walkSaved);
    }

    private static String getSubjectForNewWalk(String volunteerName, String operation) {
        return "Volunteer " + volunteerName + " " +operation;
    }

    public Walk updatedWalk(WalkDto walkDto) throws WalkNotFoundException, VolunteerNotFoundException, DogNotFoundException {
        Walk walkFromRepo = walkRepository.findById(walkDto.getId()).orElseThrow(WalkNotFoundException::new);
        Volunteer volunteerFromRepo = volunteerRepository.findVolunteerByName(walkDto.getVolunteerName()).orElseThrow(VolunteerNotFoundException::new);
        Dog dogFromRepo = dogRepository.findDogByName(walkDto.getDogName()).orElseThrow(DogNotFoundException::new);
        walkFromRepo.setVolunteer(volunteerFromRepo);
        walkFromRepo.setDog(dogFromRepo);
        walkFromRepo.setWalkDate(walkDto.getWalkDate());

        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject("Volunteer "+walkFromRepo.getVolunteer().getName()+" updated a walk")
                        .message(walkFromRepo.getVolunteer().getName()+" updated a walk with "+ walkFromRepo.getDog().getName()+
                                ", new walk date: "+ walkFromRepo.getWalkDate())
                        .build());

        return walkRepository.save(walkFromRepo);
    }

    public void deleteWalk(Long id) throws WalkNotFoundException {
        try {
            walkRepository.deleteById(id);
        } catch ( Exception e) {
            throw new WalkNotFoundException();
        }
    }

    public void editWalk(WalkDto walkDto) throws WalkNotFoundException, DogNotFoundException, VolunteerNotFoundException {
        Walk walk = walkRepository.findById(walkDto.getId()).orElseThrow(WalkNotFoundException::new);
        Dog dog = dogRepository.findDogByName(walkDto.getDogName()).orElseThrow(DogNotFoundException::new);
        Volunteer volunteer = volunteerRepository.findVolunteerByName(walkDto.getVolunteerName()).orElseThrow(VolunteerNotFoundException::new);
        walk.setWalkDate(walkDto.getWalkDate());
        walk.setDog(dog);
        walk.setVolunteer(volunteer);
        walkRepository.save(walk);

        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject("Volunteer "+walk.getVolunteer().getName()+" updated a walk")
                        .message(walk.getVolunteer().getName()+" updated a walk with "+ walk.getDog().getName()+
                                ", new walk date: "+ walk.getWalkDate())
                        .build());
    }

    public List<Walk> findAllRunningWalks() {
        return walkRepository.findAllByWalkDateIsAfter(LocalDate.now().minusDays(1L));
    }

    private void sendConfirmToVolunteer(Walk walk) throws WalkNotFoundException, VolunteerNotFoundException {
        Walk walk1 = walkRepository.findById(walk.getId()).orElseThrow(WalkNotFoundException::new);
        Volunteer volunteer = volunteerRepository.findById(walk1.getVolunteer().getId()).orElseThrow(VolunteerNotFoundException::new);
        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(volunteer.getEmail())
                        .subject(SUBJECT)
                        .message(SUBJECT+volunteer.getName()+" walk date: "
                                +walk.getWalkDate() + " with dog "+walk1.getDog())
                        .build());
    }

    private void sendConfirmToAdmin(Walk walkSaved){
        simpleEmailService.send(
                new Mail.MailBuilder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(getSubjectForNewWalk(walkSaved.getVolunteer().getName(),  "registered new walk"))
                        .message(walkSaved.getVolunteer().getName()+" registered new walk with "+ walkSaved.getDog().getName()+
                                ", walk date: "+ walkSaved.getWalkDate())
                        .build());
    }

    public long count() {

        return walkRepository.count();
    }
}
