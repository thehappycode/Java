package com.thehappycode.pagingandsorting.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.pagingandsorting.model.Course;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    // The interface body is actually empty
}
