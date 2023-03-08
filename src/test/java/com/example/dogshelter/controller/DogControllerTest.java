package com.example.dogshelter.controller;

import com.example.dogshelter.dto.DogDto;
import com.example.dogshelter.facade.DogFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DogController.class)
class DogControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DogFacade dogFacade;


    @Test
    void shouldFetchEmptyList() throws Exception {
        // Given
        when(dogFacade.getAllDogs()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dogs"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }


    @Test
    void shouldFetchDogList() throws Exception {
        // Given
        List<DogDto> dogDtoList = List.of(
                new DogDto(1L, "Ali", "Mixed", false),
                new DogDto(2L, "Ares", "Labrador", true));
        when(dogFacade.getAllDogs()).thenReturn(dogDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dogs"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Ares")));
    }


    @Test
    void shouldAddDog() throws Exception {
        //Given
        DogDto ali = new DogDto(1L, "Ali", "Mixed", false);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(ali);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200)) ;

    }

}