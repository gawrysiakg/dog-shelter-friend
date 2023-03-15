package com.example.dogshelter.mapper;

import com.example.dogshelter.domain.Image;
import com.example.dogshelter.dto.ImageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageMapperTest {

    @Autowired
    private ImageMapper imageMapper;

    @Test
    void shouldMapToImageDto(){
        //Given
        Image image = new Image( "www.example.com");
        //When
        ImageDto imageDto = imageMapper.mapToImageDto(image);
        //Then
        assertEquals("www.example.com", imageDto.getUrl());
    }

    @Test
    void shouldMapToImageDtoList(){
        //Given
        List <Image> images = List.of(
                new Image( "www.example.com"),
                new Image( "www.second.com"));
        //When
        List<ImageDto> imageDtoList = imageMapper.mapToImageDtoList(images);
        //Then
        assertEquals("www.example.com", imageDtoList.get(0).getUrl());
        assertEquals("www.second.com", imageDtoList.get(1).getUrl());
    }
}