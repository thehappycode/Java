package com.thehappycode.validation;

import com.thehappycode.validation.model.Course;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class ValidationApplication {

    private final Logger logger = LoggerFactory.getLogger(ValidationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);
	}

    @Bean
    public CommandLineRunner run() {
        Course course = new Course(1, "", "Science", 8);
        logger.info("-> " + course.toString());
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        violations.forEach(courseConstraintViolation
                -> logger.error(
                    "-> A constraint violation has occurred. Violation detail: [{}].",
                    courseConstraintViolation)
        );
        return args -> {
         logger.info("-> End CommandLineRunner");
        };

    }
}
