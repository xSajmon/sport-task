package com.simon.sporttask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simon.sporttask.configuration.MapperConfig;
import com.simon.sporttask.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MapperConfig.class})
public class MapperTest {

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void Should_IgnoreUnknownFields(){
        assertFalse(mapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

    @Test
    public void Should_RegisterJavaTimeModule(){
        assertTrue(mapper.canSerialize(ZonedDateTime.class));
    }

    @Test
    public void Should_SetPropertyNamingStrategyToSnakeCase(){
        assertEquals(mapper.getDeserializationConfig().getPropertyNamingStrategy(), PropertyNamingStrategies.SNAKE_CASE);
    }

    @Test
    public void Should_ProperlyDeserializeObject() throws IOException {
        Event event = mapper.readValue(new ClassPathResource("test.json").getFile(), Event.class);

        assertThat(event.getProbabilityAwayWin()).isEqualTo(9.4);
        assertThat(event.getCompetitors().size()).isEqualTo(2);
        assertThat(event).hasNoNullFieldsOrProperties();
    }



}
