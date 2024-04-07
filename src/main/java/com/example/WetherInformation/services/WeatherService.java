package com.example.WetherInformation.services;

import com.example.WetherInformation.dto.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherService {

    public String getWeather(String city, String date){

        String weatherData = null;
        String encodedCity = null;

        String apiUrl = "https://gg-backend-assignment.azurewebsites.net/api/Weather";
        String apiCode = "KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg==";

        try{
           try{
               encodedCity = URLEncoder.encode(city, "UTF-8");
            }
           catch(UnsupportedEncodingException e){
               e.printStackTrace();
           }

            // Construct the URL with parameters
            String url = apiUrl + "?code=" + apiCode + "&city=" + encodedCity + "&date=" + date;

           //create Http client
            HttpClient client = HttpClient.newHttpClient();

           //create Http request
            HttpRequest request = HttpRequest.newBuilder()
                                  .uri(URI.create(url))
                                   .build();

            // Send the request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response body
            System.out.println("Response body:");
            System.out.println(response.body());

            // Print the status code
            System.out.println("Status code: " + response.statusCode());
            ObjectMapper mapper = new ObjectMapper();
            if ( response.statusCode() == 200)
            {
                WeatherResponse weatherResponse= mapper.readValue(response.body(),WeatherResponse.class);
                weatherData = weatherResponse.getWeather();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return weatherData;
    }
}
