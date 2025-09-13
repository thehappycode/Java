package com.thehappycode.product;

import java.util.regex.Matcher;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    //given<Condition>When<Action>Then<ExpectedResult>
    @Test
	void givenCreateProductRequestWhenPostThenReturnCreateProductResponseDto() {
	    String requestBody = """
            {
                "id": "68c52ebc094f5788446df49f",
                "name": "Iphone 15",
                "description": "IPhone 15 ís smart phone from Apple",
                "price": 1000
            }
        """;

        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/api/product")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", Matchers.notNullValue())
            .body("name", Matchers.equalTo("Iphone 15"))
            .body("description", Matchers.equalTo("IPhone 15 ís smart phone from Apple"))
            .body("price",  Matchers.equalTo(1000));

    }

}
