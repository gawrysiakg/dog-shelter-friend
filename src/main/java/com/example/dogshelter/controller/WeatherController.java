package com.example.dogshelter.controller;

import com.example.dogshelter.domain.api.open_meteo.Weather;
import com.example.dogshelter.facade.WeatherFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {

    private final WeatherFacade weatherFacade;

    @GetMapping
    public ResponseEntity<Weather> fetchWeather(){
        return ResponseEntity.ok(weatherFacade.getWeather());
    }
}
