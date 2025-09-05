package com.course_service.handler;

import com.course_service.dto.ErrorDto;
import com.course_service.dto.ServiceResponse;
import com.course_service.exception.CourseServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationGlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception)
    {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(org.springframework.http.HttpStatus.BAD_REQUEST);
        serviceResponse.setResponse(null);

        List<ErrorDto> errorDtoList = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDto(error.getDefaultMessage()))
                .collect(Collectors.toList());
        LOGGER.error("Validation errors: {}", errorDtoList);

        serviceResponse.setErrorDto(errorDtoList); // Assuming your ServiceResponse has a method to set a list of errors
        return serviceResponse;
    }
    public ServiceResponse<?> handleCourseServiceException(CourseServiceException exception)
    {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(org.springframework.http.HttpStatus.BAD_REQUEST);
        serviceResponse.setResponse(null);
        List<ErrorDto> errorDtoList = new ArrayList<>();
        errorDtoList.add(new ErrorDto(exception.getMessage()));
        serviceResponse.setErrorDto(errorDtoList); // Assuming your ServiceResponse has a method to set a list of errors
        LOGGER.error("CourseServiceException: {}", exception.getMessage());
        return serviceResponse;
    }
}
