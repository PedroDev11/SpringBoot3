package com.coach.springcoredemocoach.common;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {

    // Constructor
    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Run a hard 5k";
    }

}
