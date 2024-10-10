package com.springdemo.mvc.validationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CouseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
    // Define a coursePrefix that we can validate it against. So this is the actual prefix that they've
    // set up with the annotation to say, hey, we validate it against a given prefix. I need to actually 
    // assign when this annotation or constraint validator is initialized
    private String coursePrefix;

    // Assign the value passed in from our annotation
    @Override
    public void initialize(CourseCode theCourseCode) {
        // that value actually accesses the attribute value for that given annotation (value="LUV")
        coursePrefix = theCourseCode.value(); // "LUV"
    }

    // HTML Form Data entered by the user (theCode)
    // You can place additional error messages here (context)
    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext context) {
        // Validation logic
        // Test if the form data starts with our course prefix
        boolean result;

        if (theCode != null) {
            result = theCode.startsWith(coursePrefix); // Starts with "LUV"            
        } else {
            result = true;
        }
        
        return result;
    }

}
