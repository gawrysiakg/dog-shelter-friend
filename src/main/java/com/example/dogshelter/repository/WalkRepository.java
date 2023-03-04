package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Walk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WalkRepository extends CrudRepository<Walk, Long> {

    @Override
    List<Walk> findAll();

    List<Walk> findAllByWalkDateIsAfter(LocalDate localDate);

    List<Walk> findAllByVolunteerNameAndWalkDateIsAfter(String username, LocalDate localDate);

}
