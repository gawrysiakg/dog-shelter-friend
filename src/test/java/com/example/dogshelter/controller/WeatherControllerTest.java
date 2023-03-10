package com.example.dogshelter.controller;

import com.example.dogshelter.domain.api.open_meteo.Daily;
import com.example.dogshelter.domain.api.open_meteo.DailyUnits;
import com.example.dogshelter.domain.api.open_meteo.Weather;
import com.example.dogshelter.facade.WeatherFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherFacade weatherFacade;


    @Test
    void shouldFetchWeather() throws Exception {
        //Given
        DailyUnits dailyUnits = new DailyUnits("2023-03-10", "°C", "°C", "mm", "%", "km/h");
        Daily daily = new Daily(new ArrayList<String>(), new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Integer>(), new ArrayList<Double>());
        Weather weather = new Weather(dailyUnits, daily);
        when(weatherFacade.getWeather()).thenReturn(weather);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daily_units.windspeed_10m_max", Matchers.is("km/h")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daily.windspeed_10m_max", Matchers.hasSize(0)));
    }
}