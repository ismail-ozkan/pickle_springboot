package com.pickle.pickledemo.tests;

import com.pickle.pickledemo.utilities.Environment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class BaseTest {

    @BeforeAll
    public static void init() {
        baseURI = Environment.getVariable("baseURL");
    }

    @AfterEach
    public void teardown() {
        reset();
    }


}
