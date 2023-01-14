package com.simon.sporttask.controller;

import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private static final String EXPOSING_EVENTS = "Exposing most probable events";
    private static final String EXPOSING_EVENT_NAMES = "Exposing competitor names";

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents(
            @RequestParam(required = false, defaultValue = "10") Long limit){
        log.info(EXPOSING_EVENTS);
        return ResponseEntity.ok(eventService.getEventsByProbability(limit));
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getCompetitorNames(){
        log.info(EXPOSING_EVENT_NAMES);
        return ResponseEntity.ok(eventService.getCompetitorNames());
    }
}
