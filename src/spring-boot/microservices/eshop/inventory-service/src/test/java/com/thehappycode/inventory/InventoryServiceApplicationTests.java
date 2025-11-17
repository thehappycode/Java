package com.thehappycode.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class InventoryServiceApplicationTests {

	@Test
	void contextLoads() {
	}

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
class InventoryServiceApplicationTests {

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
	void readInventoryRequestWhenGetThenReturnBoolean() {
        var response = RestAssured.given()
            .when()
            .get("/api/inventory?skuCode=iphone_15&quantity=100")
            .then()
            .log()
            .all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .as(Boolean.class);
        assertTrue(response);

        var negativeReponse = RestAssured.given()
            .when()
            .get("/api/inventory?skuCode=iphone_15&quantity=101")
            .then()
            .log()
            .all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .as(Boolean.class);
        assertFalse(negativeReponse);
    } 
}
