package com.example.WetherInformation.dto;

import com.example.WetherInformation.models.Event;
import lombok.Data;

import java.util.List;
@Data
public class FindEventResponse {
    List<EventResponse> events;
    private int page;
    private int pageSize;
    private int totalEvents;
    private int totalPages;
}
