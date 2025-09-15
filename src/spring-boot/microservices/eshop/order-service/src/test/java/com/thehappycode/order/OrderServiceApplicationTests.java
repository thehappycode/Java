package com.thehappycode.order;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
       mysqlContainer.start();
    }

    //given<Condition>When<Action>Then<ExpectedResult>
    @Test
	void givenOrderRequestWhenPostThenReturnString() {
        String order = """
           {
                "skuCode": "iPhone 17 512GB",
                "price": 999,
                "quantity": 10
            }
        """;

        var responseBodyString = RestAssured.given()
            .contentType("application/json")
            .body(order)
            .when()
            .post("/api/order")
            .then()
            .log()
            .all()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .body()
            .asString();
        
        assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));


    }    
}

