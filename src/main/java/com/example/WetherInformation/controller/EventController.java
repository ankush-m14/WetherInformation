package com.example.WetherInformation.controller;

import com.example.WetherInformation.dto.EventRequest;
import com.example.WetherInformation.dto.EventResponse;
import com.example.WetherInformation.dto.FindEventResponse;
import com.example.WetherInformation.services.EventFindServices;
import com.example.WetherInformation.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventFindServices eventFindServices;


    @PostMapping("/add")
   public ResponseEntity<String> addEvent(@RequestBody EventRequest eventRequest){
     eventService.addEvent(eventRequest);
     return ResponseEntity.ok("Event Successfully Added.");
   }

   @GetMapping("/find")
   public ResponseEntity <FindEventResponse> findEvents(
           @RequestParam("latitude") double latitude,
           @RequestParam("longitude") double longitude,
           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

       // Find events using EventFinderService
       int page = 1;
       FindEventResponse events = eventFindServices.findEvents(latitude, longitude, date, page);
       return ResponseEntity.ok(events);
   }
}
