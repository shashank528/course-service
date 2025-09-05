package com.course_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponseDto
{
        private int courseId;
        private String name;
        private String trainerName;
        private String duration;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private Date startDate;
        private String courseType;
        private double fees;
        private boolean isCertificateAvailable;
        private String description;
        private String email;
        private String contact;
        private String courseUniqueId;

}
