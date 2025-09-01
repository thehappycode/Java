package com.thehappycode.springdata.repository;

import org.springframework.stereotype.Repository;

import com.thehappycode.springdata.model.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course, Long> {
    // The interface body is actually empty
}
