package com.thehappycode.criteriaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.criteriaapi.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    // The interface body is actually
}
