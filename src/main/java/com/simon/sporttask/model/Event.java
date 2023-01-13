package com.simon.sporttask.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Event {
    private ZonedDateTime startDate;
    private String sportName;
    private String competitionName;
    private String seasonName;
    private List<Competitor> competitors;
    private Venue venue;
    @JsonProperty("probability_home_team_winner")
    private Double probabilityHomeWin;
    private Double probabilityDraw;
    @JsonProperty("probability_away_team_winner")
    private Double probabilityAwayWin;
}
