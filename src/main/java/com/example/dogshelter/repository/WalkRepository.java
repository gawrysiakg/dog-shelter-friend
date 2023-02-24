package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Walk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends CrudRepository<Walk, Long> {
}
