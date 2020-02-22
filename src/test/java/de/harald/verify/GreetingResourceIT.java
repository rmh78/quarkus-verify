package de.harald.verify;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

public class GreetingResourceIT {

    @Test
    public void testDatabaseEndpoint() {
        given()
          .when().get("/hello/db")
          .then()
             .statusCode(200)
             .body("id", is(1))
             .body("message", is("hello"));
    }

    @Test
    public void testKafkaEndpoint() {
        given()
            .when().get("/hello/kafka")
            .then()
            .statusCode(200)
            .body("$", hasItem("world"));
    }
}