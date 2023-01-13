package com.simon.sporttask.model;

import lombok.Data;

@Data
public class Venue {
    private String name;
    private Long capacity;
    private String cityName;
    private String mapCoordinates;
    private String countryCode;
}
