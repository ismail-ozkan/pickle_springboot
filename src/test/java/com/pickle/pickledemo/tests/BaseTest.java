package com.pickle.pickledemo.tests;

import com.pickle.pickledemo.utilities.Environment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class BaseTest {

    @BeforeAll
    public static void init() {
        baseURI = Environment.getVariable("baseURL");

        //REQUEST SPECIFICATION
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setAccept(ContentType.JSON);
        requestSpecBuilder.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder.build();


        //RESPONSE SPECIFICATION
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        // responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType(ContentType.JSON);
        //responseSpecBuilder.expectResponseTime(Matchers.lessThan(2000L));

        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @AfterEach
    public void teardown() {
        reset();
    }


}
