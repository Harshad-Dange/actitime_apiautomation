package stepdefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
    public RequestSpecification requestSpecification;
    public Response response;
   public static int customerId;
   public static int projectId;
   public static int userId;
}
