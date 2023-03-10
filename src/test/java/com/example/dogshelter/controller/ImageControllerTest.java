package com.example.dogshelter.controller;

import com.example.dogshelter.api.cloudinary.CloudinaryClient;
import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.dto.ImageDto;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.facade.ImageFacade;
import com.example.dogshelter.repository.CloudinaryRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ImageController.class)
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ImageFacade imageFacade;
    @MockBean
    private CloudinaryClient cloudinaryClient;
    @MockBean
    private CloudinaryRepository cloudinaryRepository;


    @Test
    void shouldFetchEmptyList() throws Exception {
        // Given
        when(imageFacade.findAll()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gallery"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchImageList() throws Exception {
        // Given
        List<ImageDto> imageDtoList = List.of(
                new ImageDto("www.picture.pl/dcdvdfv.jpg" ),
                new ImageDto("www.picture.pl/488d58x.jpg" ));
        when(imageFacade.findAll()).thenReturn(imageDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gallery"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].url", Matchers.is("www.picture.pl/dcdvdfv.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].url", Matchers.is("www.picture.pl/488d58x.jpg")));
    }

    @Test
    void shouldAddImage() throws Exception {
        //Given
        ImageDto image = new ImageDto("www.picture.pl/exampledog.jpg" );
        when(imageFacade.uploadImage(any(ImageDto.class))).thenReturn(image);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(image);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/gallery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.url", Matchers.is("www.picture.pl/exampledog.jpg")));
    }

    @Test
    void shouldDeleteImage() throws Exception {
        String url= "www.picture.pl/exampledog.jpg";
        doNothing().when(imageFacade).deleteImage(url);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/gallery"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
