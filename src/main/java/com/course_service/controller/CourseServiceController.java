package com.course_service.controller;

import com.course_service.dto.CourseRequestDto;
import com.course_service.dto.CourseResponseDto;
import com.course_service.dto.ServiceResponse;
import com.course_service.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceController.class);
    @Autowired
    private CourseService courseService;
    @Operation(summary = "Add a new course", description = "Adds a new course to the system and returns the created course details.")
   @ApiResponses(value ={
           @ApiResponse(responseCode = "201", description = "Course created successfully" ,
                   content = {@io.swagger.v3.oas.annotations.media.Content(
                           mediaType = "application/json",
                           schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CourseResponseDto.class)
                   )}
           ),
           @ApiResponse(responseCode = "400", description = "Invalid input data" )
   })
    @PostMapping("/addCourse")
    public ServiceResponse<CourseResponseDto> addCourse(@RequestBody @Valid CourseRequestDto courseRequestDto)
    {
        LOGGER.info("Request received for adding course: {}", courseRequestDto);
        CourseResponseDto courseResponseDto = courseService.addCourse(courseRequestDto);
        LOGGER.info("Course added successfully: {}", courseResponseDto);
        return new ServiceResponse<>(HttpStatus.CREATED,courseResponseDto);
    }
    @Operation(summary = "Add multiple courses", description = "Adds multiple courses to the system and returns the created course details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Courses created successfully",
                    content = {@io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CourseResponseDto.class)
                    )}
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/addCourses")
    public ResponseEntity<?> addCourses(@RequestBody @Valid List<CourseRequestDto> courseRequestDtos)
    {
        LOGGER.info("Request received for adding multiple courses: {}", courseRequestDtos);
        return new ResponseEntity<>(courseService.addAllCourse(courseRequestDtos),HttpStatus.CREATED);

    }
    @Operation(summary = "Get all courses", description = "Fetches all courses from the system and returns their details.")
    @GetMapping
    public ServiceResponse<List<CourseResponseDto>> getAllCourse()
    {
        LOGGER.info("Request received for fetching all courses");
       List<CourseResponseDto> courseResponseDtos = courseService.getAllCourse();
       return new ServiceResponse<>(HttpStatus.OK,courseResponseDtos);
    }
    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDto> findCourse(@PathVariable int courseId)
    {
        LOGGER.info("Request received for fetching course with ID: {}", courseId);
        CourseResponseDto courseResponseDto = courseService.findByCourseId(courseId);
        LOGGER.info("Course fetched successfully: {}", courseResponseDto);
        return new ServiceResponse<>(HttpStatus.OK,courseResponseDto);
    }

    @Operation(summary = "Delete a course", description = "Deletes a course from the system based on the provided course ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable int courseId)
    {
        LOGGER.info("Request received for deleting course with ID: {}", courseId);
        courseService.deleteCourse(courseId);
    }


    @Operation(summary = "Update an existing course", description = "Updates the details of an existing course and returns the updated course details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully",
                    content = {@io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CourseResponseDto.class)
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDto> updateCourse(@PathVariable int courseId,@RequestBody CourseRequestDto courseRequestDto)
    {
        LOGGER.info("Request received for updating course with ID: {}", courseId);
        CourseResponseDto courseResponseDto = courseService.updateCourse(courseId,courseRequestDto);
        LOGGER.info("Course updated successfully: {}", courseResponseDto);
        return new ServiceResponse<>(HttpStatus.OK,courseResponseDto);
    }


    @Operation(summary = "Find course by ID using query parameter", description = "Fetches a course by its ID using a query parameter and returns the course details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course fetched successfully",
                    content = {@io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CourseResponseDto.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/search/query")
    public ServiceResponse<CourseResponseDto> findCourseByIdUsingQueryParam(@RequestParam(required = false)int courseId)
    {
        LOGGER.info("Request received for fetching course with ID: {}", courseId);
        CourseResponseDto courseResponseDto = courseService.findByCourseId(courseId);
        LOGGER.info("Course fetched successfully: {}", courseResponseDto);
        return new ServiceResponse<>(HttpStatus.OK,courseResponseDto);
    }
}
