package com.coach.springcoredemocoach.common;

public class SwimCoach implements Coach {

    // Constructor
    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Swim 1000 meters as a warm up";
    }
}
