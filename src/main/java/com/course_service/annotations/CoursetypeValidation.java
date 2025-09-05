package com.course_service.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CoursetypeValidation {
    String message() default "Course type must be either 'live' or 'recorded";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
