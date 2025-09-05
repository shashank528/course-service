package com.course_service.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseTypeValidator implements ConstraintValidator<CoursetypeValidation,String> {
    @Override
    public boolean isValid(String courseType, ConstraintValidatorContext constraintValidatorContext) {
        if(courseType.equalsIgnoreCase("live") || courseType.equalsIgnoreCase("recorded")){
            return true;
        }
        return false;
    }
}
