package com.example.dogshelter.controller;

import com.example.dogshelter.dto.DogBreedDto;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
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
    void shouldFetchDogListByBreed() throws Exception {
        // Given
        DogBreedDto dogBreedDto = new DogBreedDto();
        dogBreedDto.setBreed("Mixed");
        List<DogDto> dogDtoList = List.of(
                new DogDto(1L, "Ali", "Mixed", false),
                new DogDto(2L, "Ares", "Mixed", true));
        when(dogFacade.getAllDogsByBreed("Mixed")).thenReturn(dogDtoList);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(dogBreedDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dogs")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Ares")));
    }

    @Test
    void shouldGetDogById() throws Exception {
        //Given
        DogDto ali = new DogDto(1L, "Ali", "Mixed", false);
        when(dogFacade.getDogById(anyLong())).thenReturn(ali);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dogs/"+ali.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Ali")));
    }

    @Test
    void shouldGetDogByName() throws Exception {
        //Given
        DogDto ali = new DogDto(1L, "Ali", "Mixed", false);
        when(dogFacade.getDogByName(anyString())).thenReturn(ali);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dogs/name/"+ali.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Ali")));
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

    @Test
    void shouldPatchDog() throws Exception {
        //Given
        DogDto ali = new DogDto(1L, "Ali", "Mixed", false);
        when(dogFacade.updateDog(any(DogDto.class))).thenReturn(ali);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(ali);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/v1/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
               // .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Ali")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.breed", Matchers.is("Mixed")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inShelter", Matchers.is(false)));
    }


    @Test
    void shouldDeleteDog() throws Exception {
        //Given
        doNothing().when(dogFacade).deleteDog(anyLong());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}