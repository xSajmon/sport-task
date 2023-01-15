package com.simon.sporttask;


import com.simon.sporttask.configuration.ModelMapperConfig;
import com.simon.sporttask.model.Competitor;
import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.model.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ModelMapperConfig.class})
public class ModelMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void Should_MapEventToDto_When_ObjectIsValid(){
        //given
        Event event = buildEvent();

        //when
        EventDto eventDto = modelMapper.map(event, EventDto.class);

        //then
        modelMapper.validate();
        assertEquals(eventDto.getEventDate(), "2021-08-04 05:00:00");
        assertEquals(eventDto.getEvent(), "FK Spartak Moscow (Russia) vs. Benfica Lisbon (Portugal)");
        assertEquals(eventDto.getVenue(), "Otkritie Arena");
        assertEquals(eventDto.getMatchResult().name(), "DRAW");
        assertEquals(eventDto.getProbability(), 43.7);

    }

    private Event buildEvent() {
        return Event.builder()
                .startDate(ZonedDateTime.parse("2021-08-04T17:00:00+00:00"))
                .competitors(List.of(
                        Competitor.builder()
                                .name("FK Spartak Moscow")
                                .country("Russia")
                                .build(),
                        Competitor.builder()
                                .name("Benfica Lisbon")
                                .country("Portugal")
                                .build()
                ))
                .venue(Venue.builder()
                        .name("Otkritie Arena")
                        .build())
                .probabilityHomeWin(27.7)
                .probabilityDraw(43.7)
                .probabilityAwayWin(28.7)
                .build();
    }

}
