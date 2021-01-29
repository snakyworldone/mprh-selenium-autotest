package com.monportailrh.utilities;

import com.monportailrh.utilities.models.Credentials;
import com.monportailrh.utilities.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredUtilityManager {
    private static String SSO_ENDPOINT_API = GeneralPropertyManger.SSO_ENDPOINT_API;
    private static String CLIENT_ID = GeneralPropertyManger.CLIENT_ID;
    private RequestSpecification fieldsRequestSpecification;
    private ResponseSpecification fieldResponseSpecification;

    /**
     * Constructor that sets default request/response specification
     *
     * @param userCredentials represents User credentials, used to set up actual User
     */
    public RestAssuredUtilityManager(Credentials userCredentials) {
        fieldsRequestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + getBearerToken(userCredentials))
                .addHeader("Accept-Language", "en")
                .setContentType(ContentType.JSON)
                .build();
        fieldResponseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
    }

    /**
     * Method that returns session information (expiration time, token, token type, etc.)
     */
    private Response generateToken(Credentials userCredentials) {
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("client_id", CLIENT_ID)
                .formParam("username", userCredentials.getUsername())
                .formParam("password", userCredentials.getPassword())
                .when()
                .post(SSO_ENDPOINT_API)
                .then().extract().response();
    }

    /**
     * Method that returns Bearer token
     */
    public String getBearerToken(Credentials userCredentials) {
        return generateToken(userCredentials).path("access_token");
    }

    private JsonPath getMeInfo() {
        String meInfoEndpoint = "/me/info";
        return given(fieldsRequestSpecification)
                .param("include", "company")
                .expect().spec(fieldResponseSpecification).statusCode(200)
                .when().get(meInfoEndpoint)
                .then().extract().body().jsonPath();
    }

    public User getUser() {
        return getMeInfo().getObject("data", User.class);
    }
}
