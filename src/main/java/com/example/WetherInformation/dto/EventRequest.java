package com.example.WetherInformation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String name;
    private String city;
    private LocalDate date;
    private LocalTime time;
    private double latitude;
    private double longitude;
}
