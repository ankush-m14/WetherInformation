package com.example.WetherInformation.services;

import com.example.WetherInformation.dto.EventRequest;
import com.example.WetherInformation.models.Event;
import com.example.WetherInformation.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void addEvent(EventRequest eventRequest){

        Event event = new Event();
        event.setName(eventRequest.getName());
        event.setCity(eventRequest.getCity());
        event.setDate(eventRequest.getDate());
        event.setTime(eventRequest.getTime());
        event.setLatitude(eventRequest.getLatitude());
        event.setLongitude(eventRequest.getLongitude());

        eventRepository.save(event);
    }
}
