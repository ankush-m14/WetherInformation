package com.example.WetherInformation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    @JsonProperty("event_name")
    private String name;

    @JsonProperty("city_name")
    private String city;

    @JsonProperty("date")
    private String data;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("distance_km")
    private String distance;
}
