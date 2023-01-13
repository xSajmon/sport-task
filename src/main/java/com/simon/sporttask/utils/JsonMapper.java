package com.simon.sporttask.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.sporttask.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JsonMapper {
    private static final String ROOT_NODE  = "Events";
    private final ObjectMapper mapper;
    @Value("${data.file}")
    private Resource jsonFile;

    public List<Event> getDataFromJson(){
        try {
            JsonNode json = mapper.readTree(jsonFile.getFile());
            String jsonAsString = mapper.writeValueAsString(json.get(ROOT_NODE));
            return mapper.readValue(jsonAsString, new TypeReference<>(){});
        } catch (IOException e){
            throw new RuntimeException("Error reading from json: " + e.getMessage());
        }
    }
}
