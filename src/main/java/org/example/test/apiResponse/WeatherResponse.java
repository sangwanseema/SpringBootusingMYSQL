package org.example.test.apiResponse;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

@Getter
@Setter
public class WeatherResponse {

    private Main main;
    private ArrayList<Weather> weather;

    @Getter
    @Setter
    public static class Main {
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        @JsonProperty("temp_min")
        private double tempMin;
        @JsonProperty("temp_max")
        private double tempMax;
        private int pressure;
        private int humidity;

    }

@Getter

@Setter
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }










}
