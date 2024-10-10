package com.coach.springcoredemocoach.common;
import org.springframework.stereotype.Component;

// Component annotation marks the class as a Spring bean makes it available for dependency injection
@Component
public class CricketCoach implements Coach {

    // Constructor
    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 minutes";
    }
}

/*
    // Bean lifecycle methods
    // Define a init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff: " + getClass().getSimpleName());
    }

    // Define a destroy method
    @PreDestroy
    public void doMyCleanStuff() {
        System.out.println("In doMyCleanStuff: " + getClass().getSimpleName());
    }
*/