package com.cybersuccess.actitime.stepdefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClass {
    public RequestSpecification requestSpecification= given();
    public Response response;
   public static int customerId;
   public static int projectId;
   public static int userId;
}
