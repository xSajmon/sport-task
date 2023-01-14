package com.simon.sporttask.service;

import com.simon.sporttask.model.Competitor;
import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.utils.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatchService {

    private final ModelMapper modelMapper;
    private final JsonMapper mapper;

    public List<Event> getEvents() {
        return mapper.getDataFromJson();
    }

    public List<EventDto> getEventsByProbability(Long number){
        return getEvents().stream().sorted(Comparator.comparing(Event::getMostLikelyResult).reversed())
                .limit(number)
                .map(element -> modelMapper.map(element, EventDto.class))
                .collect(Collectors.toList());
    }

    public List<String> getCompetitorsNames(){
        return getEvents().stream().flatMap(event -> event.getCompetitors().stream())
                .map(Competitor::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
