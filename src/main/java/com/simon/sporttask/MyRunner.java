package com.simon.sporttask;

import com.simon.sporttask.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {
    private final MatchService matchService;

    @Override
    public void run(String... args){
        System.out.println(matchService.getEvents());
    }
}
