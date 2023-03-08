package com.example.dogshelter.facade;

import com.example.dogshelter.domain.api.open_meteo.Weather;
import com.example.dogshelter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherFacade {

    private final WeatherService weatherService;

    public Weather getWeather(){
        return weatherService.getWeather();
    }
}
