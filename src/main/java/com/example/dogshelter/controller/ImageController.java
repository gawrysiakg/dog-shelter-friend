package com.example.dogshelter.controller;

import com.example.dogshelter.api.cloudinary.CloudinaryClient;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.facade.ImageFacade;
import com.example.dogshelter.repository.CloudinaryRepository;
import com.example.dogshelter.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/gallery")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImageController {

    private final ImageFacade imageFacade;


    @GetMapping
    public ResponseEntity <List<ImageDto>> getImages() {
        return ResponseEntity.ok(imageFacade.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDto> uploadImage(@RequestBody ImageDto imageDto) throws IOException {
        return ResponseEntity.ok(imageFacade.uploadImage(imageDto));
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteImage(@RequestBody ImageDto imageDto) {
       imageFacade.deleteImage(imageDto.getUrl());
        return ResponseEntity.ok().build();
    }

}
