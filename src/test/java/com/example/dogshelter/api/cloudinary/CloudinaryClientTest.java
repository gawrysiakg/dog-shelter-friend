package com.example.dogshelter.api.cloudinary;

import com.example.dogshelter.exception.DogNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CloudinaryClientTest {

    @Autowired
    CloudinaryClient cloudinaryClient;


    @Test
    void uploadFileAndSaveToDb() throws IOException {
        assertThrows(IOException.class, () ->  cloudinaryClient.uploadFileAndSaveToDb("C:/thisFileDontExist.jpg"));
    }
}