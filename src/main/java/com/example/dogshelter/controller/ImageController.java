package com.example.dogshelter.controller;

import com.example.dogshelter.api.cloudinary.CloudinaryClient;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.exception.ImageNotFoundException;
import com.example.dogshelter.exception.WalkNotFoundException;
import com.example.dogshelter.repository.CloudinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImageController {

    private final CloudinaryClient cloudinaryClient;
    private final CloudinaryRepository cloudinaryRepository;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> uploadImage(@RequestBody ImageDto imageDto) {
        cloudinaryClient.uploadFileAndSaveToDb(imageDto.getUrl());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteImage(@RequestBody ImageDto imageDto) {
        cloudinaryRepository.deleteByImageAddress(imageDto.getUrl());
        return ResponseEntity.ok().build();
    }

}
