package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepository extends CrudRepository<Image, Long> {
}
