package com.thehappycode.criteriaapi.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSES")

public class Course {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "DESCRIPTION")
    private String description;

    public Course() {

    }

    public Course(
            String name,
            String category,
            int rating,
            String description

    ) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void getDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Course)) {
            return false;
        }

        Course course = (Course) other;

        return Objects.equals(name, course.name)
                && Objects.equals(category, course.category)
                && Objects.equals(rating, course.rating)
                && Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, rating, description);
    }

    @Override
    public String toString() {
        return "Course {" +
                "id = " + id +
                ", name = " + name +
                ", category = " + category +
                ", rating = " + rating +
                ", description = " + description +
                "}";
    }
}
