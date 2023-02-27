package com.example.dogshelter.repository;

import com.example.dogshelter.domain.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudinaryRepository extends CrudRepository<Image, Long> {

    void deleteByImageAddress(String url);

    @Override
    List<Image> findAll();
}
