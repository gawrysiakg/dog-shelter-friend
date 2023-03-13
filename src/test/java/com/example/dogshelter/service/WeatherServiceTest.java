package com.example.dogshelter.service;

import com.example.dogshelter.api.open_meteo.OpenMeteoClient;
import com.example.dogshelter.domain.api.open_meteo.Daily;
import com.example.dogshelter.domain.api.open_meteo.DailyUnits;
import com.example.dogshelter.domain.api.open_meteo.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;
    @Mock
    private OpenMeteoClient openMeteoClient;

    @Test
    void shouldGetWeather(){
        DailyUnits dailyUnits = new DailyUnits("date", "°C", "°C", "mm", "%", "km/h");
        Daily daily = new Daily(List.of("2023-03-10"), List.of(8.0),List.of(5.0), List.of(3.0), List.of(0), List.of(7.0));
        Weather weather = new Weather(dailyUnits, daily);
        when(openMeteoClient.fetchWeather()).thenReturn(weather);
        //When
        Weather result = weatherService.getWeather();
        //Then
        assertNotNull(result);
        assertEquals("2023-03-10", result.getDaily().getTime().get(0));
        assertEquals(8.0, result.getDaily().getTemperature2mMax().get(0));
        assertEquals(5.0, result.getDaily().getTemperature2mMin().get(0));
        assertEquals(3.0, result.getDaily().getPrecipitationSum().get(0));
        assertEquals(0, result.getDaily().getPrecipitationProbabilityMax().get(0));
        assertEquals(7.0, result.getDaily().getWindspeed10mMax().get(0));

        assertEquals("date", result.getDailyUnits().getTime());
        assertEquals("°C", result.getDailyUnits().getTemperature2mMax());
        assertEquals("°C", result.getDailyUnits().getTemperature2mMin());
        assertEquals("mm", result.getDailyUnits().getPrecipitationSum());
        assertEquals("%", result.getDailyUnits().getPrecipitationProbabilityMax());
        assertEquals("km/h", result.getDailyUnits().getWindspeed10mMax());
    }
}