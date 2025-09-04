package com.thehappycode.namedquery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.namedquery.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    // The interface body is actually
    Iterable<Course> findAllByCategoryAndRating(String category, int rating);
}
