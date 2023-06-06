package stepdefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
   static public RequestSpecification requestSpecification;
   static public Response response;
   public static int customerId;
   public static int projectId;
   public static int userId;
}
