package com.example.WetherInformation.services;

import com.example.WetherInformation.dto.DistanceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DistanceService {
    public String calculateDistance(double latitude,double longitude,double eventlatitude,double evenlongitude )
    {
        String distance = null;

        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Distance?code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ==";


        try {
            // Construct the URL with parameters
            String url = apiUrl + "&latitude1=" + latitude + "&longitude1=" + longitude + "&latitude2=" + eventlatitude + "&longitude2=" + evenlongitude;

            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Send the request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            System.out.println("Response body:");
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            if ( response.statusCode() == 200)
            {
                DistanceResponse distanceResponse= mapper.readValue(response.body(),DistanceResponse.class);
                distance = distanceResponse.getDistance();
            }
            // Print the status code
            System.out.println("Status code: " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return distance;
   }
}
