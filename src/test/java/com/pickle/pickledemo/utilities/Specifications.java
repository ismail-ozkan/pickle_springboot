package com.pickle.pickledemo.utilities;

import com.pickle.pickledemo.pojos.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Specifications {

    private static String userId;
    private static String accessToken;
    private static String currentRole;
    private static RequestSpecification spec;

    static {
        baseURI = Environment.getVariable("baseURL");
    }

    public static String getToken(String role) {
        currentRole = role.replaceAll(" ","");
        //.replaceAll(" ","")role=role.replaceAll(" ","");
        User user = new User();
        String userEmail = Environment.getVariable(role.replaceAll(" ","")+"Email");
        String userPassword = Environment.getVariable(role.replaceAll(" ","")+"Password");
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        //todo
        accessToken = given().spec(requestSpecification).body(user).log().body().when().post("/Identity/Authentication/SignIn").then().spec(responseSpecification).extract().jsonPath().getString("result.accessToken");
        return accessToken;
    }

    public static RequestSpecification getSpec(String role) {
        if (spec != null && role.replaceAll(" ","").equals(currentRole)) {
            return spec;
        } else {
            spec = given().spec(requestSpecification).auth().oauth2(getToken(role));
            return spec;
        }

    }

    public static String getUserId(String role) {
        Response response;
        if (userId != null && role.replaceAll(" ","").equals(currentRole)) {
            response = given().auth().oauth2(accessToken).when().get("/Identity/Users");//todo
            userId = response.jsonPath().getString("result.users[0].id");//todo
            return userId;
        } else {
            response = given().spec(getSpec(role)).when().get("/Identity/Users");//todo
            userId = response.jsonPath().getString("result.users[0].id");//todo
            return userId;
        }

    }

    public static String getAccountId(String role) {
        Response response;
        if (accessToken != null && role.replaceAll(" ","").equals(currentRole)) {
            response = given().auth().oauth2(accessToken).when().get("/Identity/Users");//todo
        } else {
            response = given().spec(getSpec(role)).when().get("/Identity/Users");//todo
        }
        return response.jsonPath().getString("result.users[0].accountId");//todo
    }
}
