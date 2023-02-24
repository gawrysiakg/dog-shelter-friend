package com.example.dogshelter.api.cloudinary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CloudinaryClientTest {

    @Autowired
    CloudinaryClient cloudinaryClient;


    @Test
    void uploadFileAndSaveToDb() {
        cloudinaryClient.uploadFileAndSaveToDb("C:/pliki/me.jpg");

    }
}