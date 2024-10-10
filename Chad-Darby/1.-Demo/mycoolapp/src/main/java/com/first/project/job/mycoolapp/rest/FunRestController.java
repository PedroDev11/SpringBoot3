package com.first.project.job.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello world";
    }

    @GetMapping("/workout")
    public String workout() {
        return "It's hard";
    }

    @GetMapping("/lucky")
    public String lucky() {
        return "You are very lucky";
    }

    // Inject properties
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/team/info")
    public String teamInfo() {
        return "Coach: " + coachName + ", Team name: " + teamName;
    }
}
