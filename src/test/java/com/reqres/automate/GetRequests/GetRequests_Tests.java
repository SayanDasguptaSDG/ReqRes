package com.reqres.automate.GetRequests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetRequests_Tests {
    @BeforeTest
    public void begin_of_test() {
        RestAssured.baseURI ="https://reqres.in/";
    }

    @Test
    public void list_users() {
        given().log().all()
                .when().get("/api/users")
                .then().assertThat().log().all().statusCode(200);
    }

    @Test
    public void list_users_by_page() {
        given().log().all().queryParam("page", "2")
                .when().get("/api/users")
                .then().assertThat().log().all().statusCode(200);
    }

    @Test
    public void single_user() {
        given().log().all()
                .when().get("/api/users/2")
                .then().assertThat().log().all().statusCode(200);
    }

    @Test
    public void single_user_not_found() {
        given().log().all()
                .when().get("/api/users/23")
                .then().assertThat().log().all().statusCode(404);
    }

    @Test
    public void list_resource() {
        given().log().all()
                .when().get("/api/unknown")
                .then().assertThat().log().all().statusCode(200);
    }

    @Test
    public void single_resource() {
        given().log().all()
                .when().get("/api/unknown/3")
                .then().assertThat().log().all().statusCode(200);
    }

    @Test
    public void single_resource_not_found() {
        given().log().all()
                .when().get("/api/unknown/23")
                .then().assertThat().log().all().statusCode(404);
    }

    @Test
    public void delayed_response() {
        given().log().all().queryParam("delay", "3")
                .when().get("/api/users")
                .then().assertThat().log().all().statusCode(200);
    }
}
