package com.simon.sporttask;

import com.simon.sporttask.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {
    private final MatchService matchService;
    @Value("${events.number}")
    private Long eventsNumber;

    @Override
    public void run(String... args){
        matchService.getEventsByProbability(eventsNumber).forEach(System.out::println);
        matchService.getCompetitorsNames().forEach(System.out::println);
    }
}
