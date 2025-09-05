package com.course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceResponse <T>{
    private HttpStatus httpStatus;
    private T response;
    private List<ErrorDto> errorDto;
    public ServiceResponse(HttpStatus httpStatus, T response) {
        this.httpStatus = httpStatus;
        this.response = response;
    }

}
