package com.thehappycode.queryannotation.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thehappycode.queryannotation.model.Course;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.category=?1")
    Iterable<Course> findAllByCategory(String category);

    @Query("SELECT c FROM Course c WHERE c.category=:category AND c.rating >:rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category, @Param("rating") int rating);

    @Query(value = "SELECT * FROM COURSES WHERE RATING=?1", nativeQuery = true)
    Iterable<Course> findAllByRating(int rating);
    
    @Modifying
    @Transactional
    @Query("UPDATE Course c set c.rating=:rating WHERE c.name=:name")
    int updateCourseRatingByName(@Param("rating") int rating, @Param("name") String name);
}

