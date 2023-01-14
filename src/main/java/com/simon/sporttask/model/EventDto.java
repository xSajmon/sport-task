package com.simon.sporttask.model;

import lombok.Data;
@Data
public class EventDto {

    private String eventDate;
    private String event;
    private String venue;
    private MatchResult matchResult;
    private Double probability;

    @Override
    public String toString() {
        return String.format(
                "Start date: %s, \n" +
                "%s, \n" +
                "Venue: %s, \n" +
                "Highest probable result: %s (%.1f) \n",
                eventDate, event, venue, matchResult, probability);
    }
}
