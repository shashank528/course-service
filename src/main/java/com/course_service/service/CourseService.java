package com.course_service.service;

import com.course_service.dao.Coursedao;
import com.course_service.dto.CourseRequestDto;
import com.course_service.dto.CourseResponseDto;
import com.course_service.entity.CourseEntity;
import com.course_service.exception.CourseServiceException;
import com.course_service.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    private Coursedao coursedao;
    public List<CourseResponseDto> getAllCourse() {
       Iterable<CourseEntity> courseEntities = coursedao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::convertCourseEntitytoCourseResponseDto)
                .toList();
    }
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        CourseEntity courseEntity = AppUtils.convertCourseRequestDtotoCourseEntity(courseRequestDto);
        courseEntity = coursedao.save(courseEntity);
        return AppUtils.convertCourseEntitytoCourseResponseDto(courseEntity);
    }

    public CourseResponseDto findByCourseId(int courseId) {
        CourseEntity courseEntity = coursedao.findById(courseId).orElseThrow(()->new CourseServiceException(courseId+" is not valid course id"));
        return AppUtils.convertCourseEntitytoCourseResponseDto(courseEntity);
    }

    public void deleteCourse(int courseId) {
        CourseEntity courseEntity = coursedao.findById(courseId).orElseThrow(()->new CourseServiceException(courseId+" is not valid course id"));
        coursedao.deleteById(courseId);
    }

    public CourseResponseDto updateCourse(int courseId,CourseRequestDto courseRequestDto) {
        CourseEntity courseEntity = coursedao.findById(courseId).orElseThrow(()->new CourseServiceException(courseId+" is not valid course id"));
        courseEntity.setName(courseRequestDto.getName());
        courseEntity.setTrainerName(courseRequestDto.getTrainerName());
        courseEntity.setDuration(courseRequestDto.getDuration());
        courseEntity.setStartDate(courseRequestDto.getStartDate());
        courseEntity.setCertificateAvailable(courseRequestDto.isCertificateAvailable());
        coursedao.save(courseEntity);
        return AppUtils.convertCourseEntitytoCourseResponseDto(courseEntity);
    }

    public List<CourseResponseDto> addAllCourse(List<CourseRequestDto> courseRequestDtos) {
        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();
        for(CourseRequestDto courseRequestDto:courseRequestDtos)
        {
            courseResponseDtos.add(AppUtils.convertCourseEntitytoCourseResponseDto(coursedao.save(AppUtils.convertCourseRequestDtotoCourseEntity(courseRequestDto))));
        }
        return courseResponseDtos;
    }
}
