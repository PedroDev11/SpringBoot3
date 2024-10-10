package com.coach.springcoredemocoach.common;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    // Constructor
    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Practice your backhand volley";
    }
}
