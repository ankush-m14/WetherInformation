package com.example.WetherInformation.services;

import com.example.WetherInformation.dto.EventResponse;
import com.example.WetherInformation.dto.FindEventResponse;
import com.example.WetherInformation.models.Event;
import com.example.WetherInformation.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

@Service
public class EventFindServices {
    @Autowired
   private EventRepository eventRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
   private DistanceService distanceService;

   public FindEventResponse findEvents(double latitude, double longitude, LocalDate date, int page){
       LocalDate endDate = date.plusDays(14);

       // Query events from repository based on location and date range
       List<Event> events = eventRepository.findByDateBetween(date, endDate);

       events.sort(Comparator.comparing(Event::getDate));
       int startIndex = (page - 1) * 10;
       int endIndex = Math.min(startIndex + 10, events.size());

       List<Event> pageData =  events.subList(startIndex, endIndex);
       // Process events, fetch weather and calculate distance
       List<EventResponse> eventResponses = new ArrayList<>();


       ExecutorService executor = Executors.newFixedThreadPool(pageData.size());
       List<Future<EventResponse>> futures = new ArrayList<>();

       for (Event event : pageData) {
           Future<EventResponse> future = executor.submit(new EventProcessor(event, latitude, longitude));
           futures.add(future);
       }

       for (Future<EventResponse> future : futures) {
           try {
               EventResponse eventResponse = future.get();
               eventResponses.add(eventResponse);
           } catch (InterruptedException | ExecutionException e) {
               e.printStackTrace(); // Handle exceptions accordingly
           }
       }

       executor.shutdown(); // Shutdown the executor service

       FindEventResponse findEventResponse = new FindEventResponse();
       findEventResponse.setEvents(eventResponses);
       findEventResponse.setPage(page);
       findEventResponse.setPageSize(10);
       findEventResponse.setTotalEvents(events.size());
       findEventResponse.setTotalPages(2);

       return findEventResponse;
   }

    private class EventProcessor implements Callable<EventResponse> {
        private Event event;
        private double latitude;
        private double longitude;

        public EventProcessor(Event event, double latitude, double longitude) {
            this.event = event;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public EventResponse call() {
            String weather = weatherService.getWeather(event.getCity(), event.getDate().toString());
            String distance = distanceService.calculateDistance(latitude, longitude, event.getLatitude(), event.getLongitude());
            return new EventResponse(event.getName(), event.getCity(), event.getDate().toString(), weather, distance);
        }
   }
}
