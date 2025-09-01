package com.thehappycode.pagingandsorting;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.thehappycode.pagingandsorting.model.Course;
import com.thehappycode.pagingandsorting.repository.CourseRepository;


@DataJpaTest
class PagingAndSortingApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords(){
        Pageable pageable = PageRequest.of(0, 3);
        assertThat(courseRepository.findAll(pageable)).hasSize(3);
        assertThat(pageable.getPageNumber()).isEqualTo(0);

        Pageable nextPageable = pageable.next();
        assertThat(courseRepository.findAll(nextPageable)).hasSize(2);
        assertThat(nextPageable.getPageNumber()).isEqualTo(1);
    }

    @Test
    void givenDataAvailableWhenSortsFirstPageThenGetSortedsData(){
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("Name")));

        Condition<Course> sortedFirstCourseCondition = new Condition<Course>(){
            @Override
            public boolean matches(Course course){
                return course.getId() == 4
                    && course.getName().equals("Fully Reactive: Spring, Kotlin, and JavaFX Playing Together");
            }
        };

        assertThat(courseRepository.findAll(pageable))
            .first()
            .has(sortedFirstCourseCondition);

    }

    @Test
    void givenDataAvailableWhenApplyCustomSortThenGetSortedRusult(){
        Pageable pageable = PageRequest.of(0, 5, Sort.by("Rating").descending().and(Sort.by("Name")));

        Condition<Course> customSortFirstCourseCondition = new Condition<Course>(){
            @Override
            public boolean matches(Course course){
                return course.getId() == 5
                    && course.getName().equals("Getting Started with Spring Cloud Kubernetes");
            }
        };

        assertThat(courseRepository.findAll(pageable))
            .first()
            .has(customSortFirstCourseCondition);
    }
}
