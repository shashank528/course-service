package com.course_service.dto;

import com.course_service.annotations.CoursetypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
    private static final Logger logger = LoggerFactory.getLogger(CourseRequestDto.class);

    @NotBlank(message = "Course name is mandatory")
    private String name;
    @NotEmpty(message = "Trainer name is should be defined")
    private String trainerName;
    @NotBlank(message = "Duration shoud not be null")
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @FutureOrPresent(message = "Start date should not be in the past")
    private Date startDate;
    @CoursetypeValidation
    private String courseType;
    @Min(value = 1500, message = "Fees should not be less than 1500")
    @Max(value = 5000, message = "Fees should not be more than 5000")
    private double fees;
    private boolean isCertificateAvailable;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10)
    private String description;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Contact number should be 10 digits")
    private String contact;
}
