package com.example.dogshelter.service;

import com.example.dogshelter.api.dog_ninja.DogNinjaClient;
import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogInfoServiceTest {

    @InjectMocks
    private DogInfoService dogInfoService;
    @Mock
    private DogNinjaClient dogNinjaClient;


    @Test
    void getDogInfo(){
        //Given
        String breed = "Labrador";
        DogInfo dogInfo = new DogInfo("www.testlink.pl", 5, 3, 4, 5, 5, 7, 0, "Labrador");
        when(dogNinjaClient.getDogInfo(breed)).thenReturn(dogInfo);
        //When
        DogInfo result = dogInfoService.getDogInfo(breed);
        //Then
        assertNotNull(result);
        assertEquals(5, result.goodWithChildren);
        assertEquals("Labrador", result.getName());
    }
}