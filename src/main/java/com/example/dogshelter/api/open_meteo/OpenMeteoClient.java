package com.example.dogshelter.api.open_meteo;

import com.example.dogshelter.domain.api.open_meteo.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
@RequiredArgsConstructor
public class OpenMeteoClient {

    private final RestTemplate restTemplate;

    public Weather fetchWeather(){
        return restTemplate.getForObject(buildUrl(),  Weather.class);
    }
//
    String url = "http://api.open-meteo.com/v1/forecast?latitude=53.13&longitude=23.16&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max,windspeed_10m_max&timezone=auto";

   private URI buildUrl(){

       return  UriComponentsBuilder.fromHttpUrl("https://api.open-meteo.com/v1/forecast")
               .queryParam("latitude", 53.13)
               .queryParam("longitude", 23.16)
               .queryParam("daily", "temperature_2m_max", "temperature_2m_min", "precipitation_sum", "precipitation_probability_max", "windspeed_10m_max")
               //.queryParam("daily", "temperature_2m_min")
               //.queryParam("daily", "precipitation_sum")
              // .queryParam("daily", "precipitation_probability_max")
               //.queryParam("daily", "windspeed_10m_max")
               .queryParam("timezone", "auto")
               .build().encode().toUri();

   }






}
