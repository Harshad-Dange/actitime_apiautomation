package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerStepDefinition extends BaseClass {

    @Given("I set up the request structure")
    public void setup(Map<String, Object> data) {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .log()
                .all();
        //check if query param is not null... if it is not null then add query params in the requestSpecification
        if (Objects.nonNull(data)) {
            requestSpecification.queryParams(data);
        }


    }

    @When("I hit an api")
    public void iHitAnApi(DataTable table) {
        //hit an api and get the response
        Map<String, String> data = table.asMaps().get(0);
        if (data.get("method").equals("GET")) {
            String pathParam = "";
            if (data.get("pathParam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");
            } else {
                response = requestSpecification.get("/" + data.get("endPoint"));

            }
        }
        response.prettyPrint(); // print the response in pretty format

    }

    @Then("I verify all customer details in the response")
    public void iVerifyAllCustomerDetailsIntTheResponse(String expectedCustomerName) {

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
   /*     String firstCustName= response.jsonPath().getString("items[0].name");
        System.out.println(firstCustName);
        Assert.assertEquals("Big Bang Company", firstCustName);
       */

        List<Map<String, Object>> itemsList = response.jsonPath().getList("items");
        System.out.println(itemsList.size()); //print the size of the item list
        System.out.println(itemsList);
        boolean actualResult = false;
        boolean actualArchived = false;
        for (Map<String, Object> customerDetails : itemsList) {
            // get the customer name from the response
            String customerName = customerDetails.get("name").toString();
            //check if the expected customer name is present in the response
            if (customerName.equals(expectedCustomerName)) {
                //get the archived value for Joda Consulting Inc customer
                actualArchived = (boolean) customerDetails.get("archived");
                System.out.println("Successfully able to verify customer name in the response");
                actualResult = true;
                break;
            }
        }
        Assert.assertTrue(actualResult); //  assert the line if actualResult variable value if not true
        Assert.assertTrue(actualArchived); // asserting the archived value in Joda Consulting Inc customer

    }
}
