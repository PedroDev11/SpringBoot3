package com.coach.springcoredemocoach.common;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {

    // Constructor
    public BaseballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Spend 30 minutes in batting practice.";
    }
}
