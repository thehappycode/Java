package com.thehappycode.validation;

import com.thehappycode.validation.model.Course;
import com.thehappycode.validation.model.User;
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

        logger.info("-> End CommandLineRunner");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

//        TODO: Course
        Course course = new Course(1, "", "Science", 8);
        Set<ConstraintViolation<Course>> courseViolations = validator.validate(course);
        courseViolations.forEach(courseConstraintViolation
                -> logger.error(
                    "-> A constraint violation has occurred. Violation detail: [{}].",
                    courseConstraintViolation)
        );

//        TODO: Users
        User user1 = new User("tamnt1", "sbip");
        Set<ConstraintViolation<User>> userViolations = validator.validate(user1);
        logger.error("Password for user1 do not adhere to the password policy");
        userViolations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user2 = new User("tamnt2", "Sbip01$4UDfg");
        userViolations = validator.validate(user2);
        if(userViolations.isEmpty()) {
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("tamnt3", "Sbip01$4UDfgggg");
        userViolations = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        userViolations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        User user4 = new User("tamnt4", "Sbip014UDfgggg");
        userViolations = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        userViolations.forEach(constraintViolation -> logger.error("Violation details: [{}].", constraintViolation.getMessage()));

        return args -> {
         logger.info("-> End CommandLineRunner");
        };

    }
}
