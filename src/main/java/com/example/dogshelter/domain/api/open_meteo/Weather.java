package com.example.dogshelter.domain.api.open_meteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @JsonProperty("daily_units")
    public DailyUnits dailyUnits;
    @JsonProperty("daily")
    public Daily daily;

}