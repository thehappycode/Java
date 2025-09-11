package com.thehappycode.info.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thehappycode.info.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}


