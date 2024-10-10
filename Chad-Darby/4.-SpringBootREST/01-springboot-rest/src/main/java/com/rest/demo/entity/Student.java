package com.rest.demo.entity;

// POJO CLASS 
public class Student {
    private String firstName;
    private String lastName;

    // No argument contructor (default)
    /* public Student() {
        
    }*/

    // Constructor generated
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
