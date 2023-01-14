package com.simon.sporttask;

import com.simon.sporttask.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {
    private final EventService eventService;
    @Value("${events.number}")
    private Long eventsNumber;

    @Override
    public void run(String... args){
        eventService.getEventsByProbability(eventsNumber).forEach(System.out::println);
        eventService.getCompetitorNames().forEach(System.out::println);
    }
}
