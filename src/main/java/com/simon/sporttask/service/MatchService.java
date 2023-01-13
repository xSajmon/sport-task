package com.simon.sporttask.service;

import com.simon.sporttask.model.Event;
import com.simon.sporttask.utils.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class MatchService {

    private final JsonMapper mapper;

    public List<Event> getEvents() {
        return mapper.getDataFromJson();
    }
}
