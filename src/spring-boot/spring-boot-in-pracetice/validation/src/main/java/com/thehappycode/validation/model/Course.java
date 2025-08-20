package com.thehappycode.validation.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Course {
    private long id;

    @NotBlank(message = "A course should not blank of name")
    private String name;

    private String category;

    @Min(value = 1, message = "A course should have a minimum of 1 ratting")
    @Max(value = 5, message = "A course should have a maximum of 5 ratting")
    private int rating;

    public Course(){

    }

    public  Course(long id, String name, String category, int ratting){
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = ratting;
    }

    public  void setRating(int rating){
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "Course {" +
                "id =" + id +
                "name =" + name +
                "category = " + category +
                "ratting = " + rating +
                "}";
    }
}
