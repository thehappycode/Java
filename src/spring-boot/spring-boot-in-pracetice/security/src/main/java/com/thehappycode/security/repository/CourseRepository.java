package com.thehappycode.security.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.security.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
