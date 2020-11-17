package com.example.security.security.functional;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class HomeControllerTest {

    @Test
    void home_controller_returns_text(){

        given()
                .when()
                .get("/")
                .then()
                .body(is(equalTo("Keep-Alive")));

    }
}
