package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

    @Override
    List<Volunteer> findAll();

    Optional<Volunteer> findVolunteerByLogin(String login);

    Optional<Volunteer> findVolunteerByEmail(String mail);

}
