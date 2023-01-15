package com.simon.sporttask.service;

import com.simon.sporttask.model.Competitor;
import com.simon.sporttask.model.Event;
import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.utils.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Spy
    private ModelMapper modelMapper;
    @Mock
    private JsonMapper jsonMapper;
    @InjectMocks
    private EventService eventService;


    @BeforeEach
    public void init(){
        List<Event> events = createList();
        given(jsonMapper.getDataFromJson()).willReturn(events);
    }

    @Test
    public void Should_ReturnExactlyThreeEvents(){
        //when
        List<EventDto> result = eventService.getEventsByProbability(3L);

        //then
        assertEquals(result.size(), 3);
    }

    /*
        Unfortunately I'm not sure how should I test methods with streams.
        I have also problem with mocking model mapper.
    */

    @Test
    public void Should_ReturnDistinctCompetitorNamesSortedLexically(){
        //when
        List<String> result = eventService.getCompetitorNames();

        //then
        assertThat(result).doesNotHaveDuplicates();
        assertThat(result).containsExactly("FC Prishtina", "Flora Tallinn", "Malmo FF", "Riga FC" );
        verify(jsonMapper, times(1)).getDataFromJson();
    }

    private List<Event> createList() {
        return List.of(
                Event.builder()
                        .competitors(List.of(
                                Competitor.builder().name("Flora Tallinn").build(),
                                Competitor.builder().name("Malmo FF").build())
                        )
                        .probabilityHomeWin(56.3).probabilityDraw(17.1).probabilityAwayWin(26.6)
                        .build(),
                Event.builder()
                        .competitors(List.of(
                                Competitor.builder().name("Flora Tallinn").build(),
                                Competitor.builder().name("FC Prishtina").build())
                        )
                        .probabilityHomeWin(29.4).probabilityDraw(40.7).probabilityAwayWin(29.9)
                        .build(),
                Event.builder()
                        .competitors(List.of(
                                Competitor.builder().name("Riga FC").build(),
                                Competitor.builder().name("Malmo FF").build())
                        )
                        .probabilityHomeWin(65.9).probabilityDraw(13.8).probabilityAwayWin(20.3)
                        .build()

        );
    }


}