package com.thehappycode.criteriaapi;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thehappycode.criteriaapi.model.Course;
import com.thehappycode.criteriaapi.repository.CourseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SpringBootTest
class CriteriaApiApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        List<Course> courses = getCourseList();
        courseRepository.saveAll(courses);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        Predicate predicate = criteriaBuilder.equal(root.get("category"), "Spring");
        criteriaQuery.where(predicate);
        TypedQuery<Course> query = entityManager.createQuery(criteriaQuery);
        Assertions.assertThat(query.getResultList().size()).isEqualTo(3);
    }

    private List<Course> getCourseList() {
        Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring", 4,
                "Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL", "Spring", 5,
                "Learn Spring Security DSL in easy steps");
        Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes", "Spring", 3,
                "Master Spring Boot application deployment with Kubernetes");
        Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5,
                "Learn Python concepts in easy steps");
        Course gameDevelopmentWithPython = new Course("Game Development with Python", "Python", 2,
                "Learn Python by developing 10 wonderful games");
        Course javaScriptForAll = new Course("JavaScript for All", "JavaScript", 4,
                "Learn basic JavaScript syntax that can apply to anywhere");
        Course javaScriptCompleteGuide = new Course("JavaScript Complete Guide", "JavaScript", 5,
                "Master JavaScript with Core Concepts and Web Development");

        return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernetesCourse,
                rapidPythonCourse, gameDevelopmentWithPython, javaScriptForAll, javaScriptCompleteGuide);
    }

}
