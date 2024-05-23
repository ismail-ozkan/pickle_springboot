package com.pickle.pickledemo.tests;

import com.pickle.pickledemo.pojos.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class DemoTest extends BaseTest {


    @Test
    @DisplayName("Demo Test1")
    public void testDemoTest1() {

        User user1 = new User("admin@pickle.com","pass12");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user1)
                .when()
                .post("/api/users/register")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();



    }

}
