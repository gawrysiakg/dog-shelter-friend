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
public class DailyUnits {

    @JsonProperty("time")
    public String time;
    @JsonProperty("temperature_2m_max")
    public String temperature2mMax;
    @JsonProperty("temperature_2m_min")
    public String temperature2mMin;
    @JsonProperty("precipitation_sum")
    public String precipitationSum;
    @JsonProperty("precipitation_probability_max")
    public String precipitationProbabilityMax;
    @JsonProperty("windspeed_10m_max")
    public String windspeed10mMax;

}