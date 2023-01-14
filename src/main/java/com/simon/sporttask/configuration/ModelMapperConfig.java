package com.simon.sporttask.configuration;

import com.simon.sporttask.model.Competitor;
import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.model.MatchResult;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Event, EventDto> typeMap = modelMapper.createTypeMap(Event.class, EventDto.class);
        Converter<List<Competitor>, String> fullEventName = ctx ->
                String.format("%s (%s) vs. %s (%s)",
                    ctx.getSource().get(0).getName(),
                    ctx.getSource().get(0).getCountry(),
                    ctx.getSource().get(1).getName(),
                    ctx.getSource().get(1).getCountry());

        Converter<ZonedDateTime, String> formattedDate = ctx ->  DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(ctx.getSource());
        Converter<Map<Integer, Double>, MatchResult> toEnum = ctx -> {
            Integer index = ctx.getSource().entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).get();
            return MatchResult.values()[index];
        };

        typeMap.addMappings(mapper -> mapper.using(formattedDate).map(Event::getStartDate, EventDto::setEventDate));
        typeMap.addMappings(mapper -> mapper.using(fullEventName).map(Event::getCompetitors, EventDto::setEvent));
        typeMap.addMappings(mapper -> mapper.using(toEnum).map(Event::getProbabilities, EventDto::setMatchResult));
        typeMap.addMapping(source -> source.getVenue().getName(), EventDto::setVenue);
        typeMap.addMapping(Event::getMostLikelyResult, EventDto::setProbability);

        return modelMapper;
    }
}
