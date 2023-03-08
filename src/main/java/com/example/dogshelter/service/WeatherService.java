package com.example.dogshelter.service;

import com.example.dogshelter.api.open_meteo.OpenMeteoClient;
import com.example.dogshelter.domain.api.open_meteo.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenMeteoClient openMeteoClient;


    public Weather getWeather(){
        return openMeteoClient.fetchWeather();
    }
}
