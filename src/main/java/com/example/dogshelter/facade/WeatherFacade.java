package com.example.dogshelter.facade;

import com.example.dogshelter.domain.api.open_meteo.Weather;
import com.example.dogshelter.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherFacade {

    private final WeatherService weatherService;

    public Weather getWeather(){
        log.info("Loading actual weather");
        return weatherService.getWeather();
    }
}
