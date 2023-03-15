package com.example.dogshelter.controller;

import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import com.example.dogshelter.facade.DogInfoFacade;
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
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DogInfoController.class)
class DogInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DogInfoFacade dogInfoFacade;

    @Test
    void shouldGetDogInfo() throws Exception {
        //Given
        String breed = "Labrador";
        DogInfo dogInfo = new DogInfo("www.testlink.pl", 5, 3, 4, 5, 5, 7, 0, "Labrador");
        when(dogInfoFacade.getDogInfo(breed)).thenReturn(dogInfo);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/info/"+breed)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image_link", Matchers.is("www.testlink.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Labrador")));
    }
}