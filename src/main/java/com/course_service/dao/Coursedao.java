package com.course_service.dao;

import com.course_service.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Coursedao extends CrudRepository<CourseEntity,Integer> {

}
