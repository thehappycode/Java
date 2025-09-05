package com.thehappycode.querydsl.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.querydsl.model.Course;

@Repository
public interface CourseRepository extends
        CrudRepository<Course, Long>,
        QuerydslPredicateExecutor<Course> {
    // The interface body is actually empty
}
