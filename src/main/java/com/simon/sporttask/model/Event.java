package com.simon.sporttask.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Event {
    private LocalDateTime date;
    private String sportName;
    private String competitionName;
    private String seasonName;
    private List<Competitor> competitors;
    private Venue venue;
    private Double probabilityHomeWin;
    private Double probabilityDraw;
    private Double probabilityAwayWin;
}
