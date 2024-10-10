package com.springdemo.mvc.validationdemo.model;

import com.springdemo.mvc.validationdemo.validation.CourseCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Must be greater than or equal to zero")
    @Max(value = 10, message = "Must be less than or equal to 10")
    private Integer freePasses;

    // Regular expression -> except 5 characters or digits
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 characters/digits")
    private String postalCode;

    @NotNull(message = "is required")
    @CourseCode
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
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
