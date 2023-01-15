package com.simon.sporttask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    private String name;
    private Long capacity;
    private String cityName;
    private String mapCoordinates;
    private String countryCode;
}
