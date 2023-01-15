package com.simon.sporttask.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


    public Double getMostLikelyResult(){
        return DoubleStream.of(probabilityHomeWin, probabilityDraw, probabilityAwayWin).max().getAsDouble();
    }

    public Map<Integer, Double> getProbabilities(){
        return Map.ofEntries(
                Map.entry(0, probabilityHomeWin),
                Map.entry(1, probabilityDraw),
                Map.entry(2, probabilityAwayWin));
    }
}
