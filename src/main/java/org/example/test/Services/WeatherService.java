package org.example.test.Services;

import org.example.test.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final  String  apiKey="feea6d304a875759f3868a99ae8d47de";
    private static final String  apiUrl="http://api.openweathermap.org/data/2.5/weather?q=CITY&appid=feea6d304a875759f3868a99ae8d47de" ;

    @Autowired
    private RestTemplate restTemplate;
    // rest template is a class in springboot which process the  http request
 //and give us response
    public WeatherResponse getWeather(String city) {

        String finalApi=apiUrl.replace("CITY", city);
      ResponseEntity<WeatherResponse>response= restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
      WeatherResponse weather=response.getBody();
       return weather;
    }
           ;
}
