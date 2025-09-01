package com.thehappycode.springdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.thehappycode.springdata.model.Course;
import com.thehappycode.springdata.repository.CourseRepository;


// @SpringBootTest
@DataJpaTest
class SpringDataApplicationTests {

    private Course course = new Course(
            "Rapid Spring Boot Application Development",
            "Spring",
            4,
            "Spring Boot gives all the power of the Spring Framework without all of the complexities"
        );

    @Autowired
    private CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}

    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOnCourse(){
        courseRepository.save(course);

        assertThat(Arrays.asList(courseRepository.findAll()).size()).isEqualTo(1);
    }
}
