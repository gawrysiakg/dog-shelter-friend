package com.example.dogshelter.controller;

import com.example.dogshelter.domain.Role;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.dto.WalkDto;
import com.example.dogshelter.facade.WalkFacade;
import com.google.gson.*;
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
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WalkController.class)
class WalkControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WalkFacade walkFacade;


    @Test
    void shouldFetchEmptyList() throws Exception {
        // Given
        when(walkFacade.getAllWalks()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/walks"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchWalksList() throws Exception {
        // Given
        List<WalkDto> walksDtoList = List.of(
                new WalkDto(1L, LocalDate.of(2023, 03, 10), "andy001", "Pola"),
                new WalkDto(2L, LocalDate.of(2023, 03, 12), "mike001", "Remo"));
        when(walkFacade.getAllWalks()).thenReturn(walksDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/walks"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].walkDate", Matchers.is("2023-03-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].volunteerName", Matchers.is("mike001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dogName", Matchers.is("Remo")));
    }

    @Test
    void shouldGetPlannedWalksForVolunteer() throws Exception {
        // Given
        VolunteerDto first = new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER);
        List<WalkDto> walksDtoList = List.of(
                new WalkDto(1L, LocalDate.of(2023, 03, 10), "andy001", "Pola"),
                new WalkDto(2L, LocalDate.of(2023, 03, 12), "mike001", "Remo"));
        when(walkFacade.getPlannedWalksForVolunteer(first.getName())).thenReturn(walksDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/walks/planned/"+first.getName()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].walkDate", Matchers.is("2023-03-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].volunteerName", Matchers.is("mike001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dogName", Matchers.is("Remo")));
    }

    @Test
    void shouldFetchWalkById() throws Exception {
        // Given
         WalkDto walkDto = new WalkDto(1L, LocalDate.of(2023, 03, 10), "andy001", "Pola");
        when(walkFacade.getWalk(walkDto.getId())).thenReturn(walkDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/walks/"+walkDto.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.walkDate", Matchers.is("2023-03-10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.volunteerName", Matchers.is("andy001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dogName", Matchers.is("Pola")));
    }

    @Test
    void shouldAddWalk() throws Exception {
        //Given
        WalkDto walk = new WalkDto(1L, LocalDate.of(2023, 03, 10), "andy001", "Pola") ;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                })
                .create();
        String jsonContent = gson.toJson(walk);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/walks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateWalk() throws Exception {
        //Given
        LocalDate walkDate = LocalDate.of(2023, 03, 10);
        WalkDto walk = new WalkDto(1L, walkDate, "andy001", "Pola") ;
        when(walkFacade.updateWalk(any(WalkDto.class))).thenReturn(walk);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                })
                .create();
        String jsonContent = gson.toJson(walk);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/walks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.volunteerName", Matchers.is("andy001")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dogName", Matchers.is("Pola")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.walkDate", Matchers.is("2023-03-10")));
    }

    @Test
    void shouldChangeWalk() throws Exception {
        //Given
        LocalDate walkDate = LocalDate.of(2023, 03, 10);
        WalkDto walk = new WalkDto(1L, walkDate, "andy001", "Pola") ;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }  })
                .create();
        String jsonContent = gson.toJson(walk);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/v1/walks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteWalk() throws Exception {
        //Given
        doNothing().when(walkFacade).deleteWalk(anyLong());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/walks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}