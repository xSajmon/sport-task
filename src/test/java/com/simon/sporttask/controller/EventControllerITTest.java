package com.simon.sporttask.controller;


import com.simon.sporttask.model.EventDto;
import com.simon.sporttask.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EventController.class)
class EventControllerITTest {

    private static final String ENDPOINT = "/events";
    private static final String ENDPOINT_NAMES = "/events/name";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    private List<EventDto> events;

    @BeforeEach
    public void init(){
        events = new ArrayList<>();
        for (int i = 0; i<10; i++){
            events.add(new EventDto());
        }

        given(eventService.getEventsByProbability(anyLong())).willAnswer(invocation -> {
            Long size = invocation.getArgument(0);
            return events.stream().limit(size).collect(Collectors.toList());
        });
    }

    @Test
    public void Should_Return200Status_When_AnyGetRequestCalled() throws Exception {

        given(eventService.getEventsByProbability(anyLong())).willReturn(anyList());

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().isOk());

        mockMvc.perform(get(ENDPOINT_NAMES))
                .andExpect(status().isOk());
    }

    @Test
    public void Should_ReturnTop10EventsByProbability_When_GetCalledWithoutParam() throws Exception {

        mockMvc.perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(10))
                .andReturn();
    }

    @Test
    public void Should_ReturnTop3EventsByProbability_When_GetCalledWithLimitParam3() throws Exception {

        mockMvc.perform(get(ENDPOINT).param("limit", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn();

    }

}