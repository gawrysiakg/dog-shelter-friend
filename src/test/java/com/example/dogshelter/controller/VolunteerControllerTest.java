package com.example.dogshelter.controller;

import com.example.dogshelter.domain.Role;
import com.example.dogshelter.dto.VolunteerDto;
import com.example.dogshelter.facade.VolunteerFacade;
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
@WebMvcTest(VolunteerController.class)
class VolunteerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VolunteerFacade volunteerFacade;


    @Test
    void shouldFetchEmptyList() throws Exception {
        // Given
        when(volunteerFacade.getAllVolunteers()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteers"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }


    @Test
    void shouldFetchVolunteersList() throws Exception {
        // Given
        List<VolunteerDto> volunteersDtoList = List.of(
                new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER),
                new VolunteerDto(2L, "Bob", "Builder", "bobbuilder", "bob", "123@bob.pl" ,999888777, Role.ADMIN));
        when(volunteerFacade.getAllVolunteers()).thenReturn(volunteersDtoList);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteers"))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].role", Matchers.is("USER")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("bobbuilder")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("123@bob.pl")));
    }

    @Test
    void shouldGetVolunteerById() throws Exception {
        //Given
        VolunteerDto first = new VolunteerDto();
        first.setId(1L);
        first.setFirstName("Andy");
        first.setLastName("Stender");
        first.setName("andy01");
        first.setPassword("pass123");
        first.setEmail("as@dy.pl");
        first.setPhone(544744744);
        first.setRole(Role.USER);
        when(volunteerFacade.getVolunteerById(first.getId())).thenReturn(first);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteers/"+first.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Andy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Stender")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("USER")));
    }

    @Test
    void shouldGetVolunteerByUsername() throws Exception {
        //Given
        VolunteerDto first = new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER);
        when(volunteerFacade.getVolunteerByName(any())).thenReturn(first);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteers/login/"+first.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Andy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Stender")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("USER")));
    }

    @Test
    void shouldGetVolunteerByEmail() throws Exception {
        //Given
        VolunteerDto first = new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER);
        when(volunteerFacade.getVolunteerByEmail(any())).thenReturn(first);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/volunteers/email/"+first.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Andy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Stender")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("USER")));
    }



    @Test
    void shouldAddVolunteer() throws Exception {
        //Given
        VolunteerDto first = new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(first);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void shouldPatchVolunteer() throws Exception {
        //Given
        VolunteerDto first = new VolunteerDto(1L, "Andy", "Stender", "andy01", "pass123", "as@dy.pl", 544744744, Role.USER);
        when(volunteerFacade.updateVolunteer(any(VolunteerDto.class))).thenReturn(first);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(first);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/v1/volunteers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Andy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Stender")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("USER")));
    }


    @Test
    void shouldDeleteVolunteer() throws Exception {
        //Given
        doNothing().when(volunteerFacade).deleteVolunteer(anyLong());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/volunteers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
