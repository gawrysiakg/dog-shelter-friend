package com.example.dogshelter.service;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.repository.CloudinaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CloudinaryServiceTest {

    @InjectMocks
    private CloudinaryService cloudinaryService;
    @Mock
    private CloudinaryRepository cloudinaryRepository;

    @Test
    void shouldGetAllImagesFromRepository(){
        //Given
        List<Image> images = List.of(new Image("www.example.pl"),
                                     new Image("www.example2.pl"))  ;
        when(cloudinaryRepository.findAll()).thenReturn(images);
        //When
        List<Image> imagesFormRepo = cloudinaryService.findAll();
        //then
        Assertions.assertEquals(2, imagesFormRepo.size());
        Assertions.assertEquals("www.example.pl", imagesFormRepo.get(0).getImageAddress());
    }



}