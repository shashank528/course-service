package com.course_service.util;

import com.course_service.dto.CourseRequestDto;
import com.course_service.dto.CourseResponseDto;
import com.course_service.entity.CourseEntity;

import java.util.UUID;

public class AppUtils {
    public static CourseResponseDto convertCourseEntitytoCourseResponseDto(CourseEntity courseEntity) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setCourseId(courseEntity.getCourseId());
        courseResponseDto.setName(courseEntity.getName());
        courseResponseDto.setTrainerName(courseEntity.getTrainerName());
        courseResponseDto.setDuration(courseEntity.getDuration());
        courseResponseDto.setStartDate(courseEntity.getStartDate());
        courseResponseDto.setCourseType(courseEntity.getCourseType());
        courseResponseDto.setFees(courseEntity.getFees());
        courseResponseDto.setCertificateAvailable(courseEntity.isCertificateAvailable());
        courseResponseDto.setDescription(courseEntity.getDescription());
        courseResponseDto.setEmail(courseEntity.getEmail());
        courseResponseDto.setContact(courseEntity.getContact());
        courseResponseDto.setCourseUniqueId(UUID.randomUUID().toString());
        return courseResponseDto;


    }
    public static CourseEntity convertCourseRequestDtotoCourseEntity(CourseRequestDto courseRequestDto){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseRequestDto.getName());
        courseEntity.setTrainerName(courseRequestDto.getTrainerName());
        courseEntity.setDuration(courseRequestDto.getDuration());
        courseEntity.setStartDate(courseRequestDto.getStartDate());
        courseEntity.setCourseType(courseRequestDto.getCourseType());
        courseEntity.setFees(courseRequestDto.getFees());
        courseEntity.setCertificateAvailable(courseRequestDto.isCertificateAvailable());
        courseEntity.setDescription(courseRequestDto.getDescription());
        courseEntity.setEmail(courseRequestDto.getEmail());
        courseEntity.setContact(courseRequestDto.getContact());
        return courseEntity;


    }
}
