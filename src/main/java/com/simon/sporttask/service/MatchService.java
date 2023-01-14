package com.simon.sporttask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.utils.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.security.KeyStore;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatchService {

    private final ModelMapper modelMapper;
    private final JsonMapper mapper;

    public List<Event> getEvents() {
        return mapper.getDataFromJson();
    }

    public List<EventDto> getTop10EventsByProbability(){
        System.out.println();
        return getEvents().stream().sorted(Comparator.comparing(Event::getMostLikelyResult).reversed())
                .limit(10)
                .map(element -> modelMapper.map(element, EventDto.class))
                .collect(Collectors.toList());
    }
}
