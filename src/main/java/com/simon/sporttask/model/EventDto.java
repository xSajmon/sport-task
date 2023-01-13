package com.simon.sporttask.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {

    private LocalDateTime eventDate;
    private String competitorHome;
    private String competitorHomeCountry;
    private String competitorAway;
    private String competitorAwayCountry;
    private String venue;
    private MatchResult matchResult;
    private Double probability;

}
