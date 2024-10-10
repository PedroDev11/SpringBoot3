package com.springdemo.mvc.validationdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Helper class that contains business rules / validation logic
@Constraint(validatedBy = CouseCodeConstraintValidator.class)
// Apply this new annotation that we're creating, you can apply this annotation on methods or fields
@Target({ElementType.METHOD, ElementType.FIELD})
// How long will the marked annotation be stored or used
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // Define default course code
    public String value() default "LUV";

    // Define default error message
    public String message() default "Must start with LUV";

    // Define defualt groups: can group related constraints
    public Class<?>[] groups() default {}; // empty collection

    // Define default payloads: provide custom detials about validation failure 
    // (severity level, error code, etc.), payload can basically give you additional details
    // about the error message that has ocurred.
    public Class<? extends Payload>[] payload() default {};

}
