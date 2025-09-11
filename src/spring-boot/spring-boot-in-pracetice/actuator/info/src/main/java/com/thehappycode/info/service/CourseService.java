package com.thehappycode.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thehappycode.info.model.Course;
import com.thehappycode.info.repository.CourseRepository;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> getAvailableCourses() {
        return courseRepository.findAll();
    }
}

