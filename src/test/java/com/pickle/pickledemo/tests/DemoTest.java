package com.pickle.pickledemo.tests;

import com.pickle.pickledemo.pojos.UserLogin;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.pickle.pickledemo.utilities.Specifications.getSpec;
import static io.restassured.RestAssured.given;

public class DemoTest extends BaseTest {


    @Test
    @DisplayName("Demo Test1")
    public void testDemoTest1() {

        UserLogin user1 = new UserLogin("admin@pickle.com", "123456");

        given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user1)
                .when()
                .post("/api/authenticate")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

    }

    @Test
    @DisplayName("Demo Test2")
    public void testDemoTest2() {

        given()
                .spec(getSpec("Admin"))
        .when()
                .get("/api/users")
        .then()
                .statusCode(200)
                .extract().response().prettyPrint();

    }

    @Test
    @DisplayName("Admin User info with POJO")
    public void testAdminUser() {
        /*given()
                .spec(getSpec("Admin"))
        .when()
                .get()*/
    }


}
