package com.cybersuccess.actitime.stepdefinitions;

import com.cybersuccess.actitime.config.ApiRequestBuilder;
import com.cybersuccess.actitime.config.PropertyHandler;
import com.cybersuccess.actitime.entities.CustomerInfo;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.CustomerPojo;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class CustomerStepDefinition extends BaseClass {
    CustomerPojo customerPayload;
    ApiRequestBuilder requestBuilder = ApiRequestBuilder.getInstance();
     int customerId;

    @Given("I set up the request structure")
    public void setup(Map<String, Object> queryParams) {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
                .log()
                .all();
        //check if query param is not null... if it is not null then add query params in the requestSpecification
        if (Objects.nonNull(queryParams)) {
            requestSpecification.queryParams(queryParams);
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
            } else if (Objects.nonNull(customerId) && customerId != 0) {
                pathParam = String.valueOf(customerId);
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");

            } else {
                response = requestSpecification.get("/" + data.get("endPoint"));

            }
        } else if (data.get("method").equals("POST")) {
            response = requestSpecification.post("/" + data.get("endPoint"));

        } else if (data.get("method").equals("PUT")) {
            if (Objects.isNull(data.get("pathParam"))) {
                response = requestSpecification.put("/" + data.get("endPoint"));
            } else if (data.get("pathParam").equals("userId")) {
                response = requestSpecification.put("/" + data.get("endPoint") + "/" + "{goRestUserId}");
            }
        } else if (data.get("method").equals("PATCH")) {
            if (Objects.isNull(data.get("pathParam"))) {
                response = requestSpecification.patch("/" + data.get("endPoint"));
            } else if (data.get("pathParam").equals("userId")) {
                response = requestSpecification.patch("/" + data.get("endPoint") + "/" + "{goRestUserId}");
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

    @Then("I verify customer response is getting sorted in {string} order")
    public void iVerifyCustomerResponseIsGettingSortedInOrder(String order) {

        //write a logic to verify response is getting sorted in the provided order

        Assert.assertEquals(200, response.getStatusCode());

        //Print all descriptions of customers
        System.out.println(response.jsonPath().getList("items.description"));

        //print all allowedActions field values
        List<Map<String, Boolean>> allowedActions = response.jsonPath().getList("items.allowedActions");
        System.out.println(allowedActions);

        //get all names from the response
        List<String> names = response.jsonPath().getList("items.name");
        System.out.println("unsorted list: " + names);
        // create an object of list and add previous list into it
        List<String> actualSortedList = new ArrayList<>();
        actualSortedList.addAll(names);

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };

        //replace above code with lambda expression

        Comparator<String> comp = (s1, s2) -> {
            return s1.compareToIgnoreCase(s2);
        };

        Comparator<String> comp1 = String::compareToIgnoreCase;


        if (order.equals("desc")) {
            //sort the list in desc order
//            Collections.reverse(names);

            Collections.sort(names, Collections.reverseOrder(comparator));
            System.out.println("Desc Order " + names);
        } else if (order.equals("asc")) {
            //sort the list in asc order
//            Collections.sort(names);

            Collections.sort(names, comparator);
            names.sort(comparator);

            System.out.println("Asc Order: " + names);
        }
        Assert.assertTrue(names.equals(actualSortedList));
    }

    @Given("I set up the request structure to create customer")
    public void iSetUpTheRequestStructureToCreateCustomer(DataTable table) {
        //Get the name of customer from feature file using datatable
        String customerName = table.asMaps().get(0).get("name");
        String archived = table.asMaps().get(0).get("archived");
        String reqBody;
        if (Objects.isNull(customerName)) {
            reqBody = "{\n" +
                    "    \"name\": null,\n" +
                    "    \"archived\": false\n" +
                    "}";
        } else if (customerName.equals("empty")) {
            reqBody = "{\n" +
                    "    \"name\": \"\",\n" +
                    "    \"archived\": false\n" +
                    "}";
        } else {
            reqBody = "{\n" +
                    "    \"name\": \"" + customerName + "\",\n" +
                    "    \"archived\": false\n" +
                    "}";
        }


        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(reqBody)
                .log()
                .all();

    }

    @Then("I verify customer is getting create succesfully with name {string}")
    public void iVerifyCustomerIsGettingCreateSuccesfullyWithName(String expectedName) {

        Assert.assertEquals(200, response.getStatusCode());

        customerId = response.jsonPath().getInt("id");

        String actualName = response.jsonPath().getString("name");

        //verify customer name from the response
        Assert.assertEquals(expectedName, actualName);

        //verify archived field  value and it should be false
        Assert.assertFalse(response.jsonPath().getBoolean("archived"));

        //verify description should be null
//        Assert.assertTrue(response.jsonPath().get("description") == null);

        Assert.assertTrue(Objects.isNull(response.jsonPath().get("description")));
    }

    @When("I set up the request structure get customer information")
    public void iSetUpTheRequestStructureGetCustomerInformation() {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log()
                .all();
    }

    @Then("I verify the status code as {int} and error message")
    public void iVerifyTheStatusCodeAsAndErrorMessageAs(int statusCode, Map<String, String> data) {

        //verify status code
        Assert.assertEquals(statusCode, response.getStatusCode());

        //verify an error message
        Assert.assertEquals(data.get("message"), response.jsonPath().getString("message"));


    }

    @Given("I set up the request structure to create customer payload")
    public void iSetUpTheRequestStructureToCreateCustomerPayload(DataTable table) {

        Map<String, String> payload = table.asMaps().get(0);

        customerPayload = new CustomerPojo();
//        condition?:
        String name = (payload.get("name").equals("random")) ? new Faker().name().firstName() : payload.get("name");
        customerPayload.setName(name);
//        if(Objects.nonNull(payload.get("archived"))){
//            customerPayload.setArchived(Boolean.valueOf(payload.get("archived")));
//        }
        Optional.ofNullable(payload.get("archived"))
                .ifPresentOrElse(val -> {
                    boolean archived = Boolean.parseBoolean(val);
                    customerPayload.setArchived(archived);
                }, () -> System.out.println("Empty value in archived"));


//        if (payload.get("name").equals("random")) {
//            payload.replace("name", new Faker().name().firstName());
//        }
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(customerPayload)
                .log().all();


    }

    @Then("I verify the status code as {int} and archived value in response")
    public void iVerifyTheStatusCodeAsAndArchivedValueInResponse(int statusCode, boolean expectedArchive) {

        //verify status code
        Assert.assertEquals(statusCode, response.getStatusCode());

        //verify archive value in response
        Assert.assertEquals(expectedArchive, response.jsonPath().getBoolean("archived"));

        customerId = response.jsonPath().getInt("id");

    }

    @Then("I verify customer is getting created successfully")
    public void iVerifyCustomerIsGettingCreatedSuccessfully() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(customerPayload.getName(), response.jsonPath().getString("name"));
        Assert.assertEquals(customerPayload.getDescription(), response.jsonPath().getString("description"));
        Assert.assertEquals(customerPayload.isArchived(), response.jsonPath().getBoolean("archived"));
        customerId = response.jsonPath().getInt("id");

    }

    @When("I delete the customer")
    public void iDeleteTheCustomer() {

        RestAssured.useRelaxedHTTPSValidation();
/*        requestSpecification = given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1/customers")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("customerId", customerId)
                .log().all()
                .delete("{customerId}")
                .then().assertThat().statusCode(204);*/
//        Assert.assertEquals(204, response.getStatusCode());

        given().baseUri("https://demo.actitime.com")
                .basePath("/api/v1/customers")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("customerId", customerId)
                .log().all()
                .when()
                .delete("{customerId}")
                .then().assertThat().statusCode(204);
    }

    @Then("I verify customer is getting deleted from the system")
    public void iVerifyCustomerIsGettingDeletedFromTheSystem() {
    }

    @Given("I set up the request structure to get all customers")
    public void iSetUpTheRequestStructureToGetAllCustomers() {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();
        //uri =  https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
//                .auth()
//                .basic("admin", "manager")  // declared in the AuthenticationSpecification interface and return RequestSpecification referance
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log()
                .all();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RequestSpecification reqSpec  = requestSpecBuilder.setBaseUri("https://demo.actitime.com")
                .setBasePath("/api/v1")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .build();

       requestSpecification = given().spec(reqSpec).log().all();

    }

    @Given("I create customer with below details")
    public void createCustomer(Map<String, String> data) throws IOException {
        PropertyHandler property = new PropertyHandler("endpoints.properties");
        String endpoint = property.getProperty(data.get("endPoint"));
        //Builder Pattern
        CustomerInfo customerDetails = CustomerInfo.builder()
                .name(new Faker().company().name())
                .description("Sample Desc")
                .build();
        requestBuilder.postRequest(customerDetails,endpoint);
        requestBuilder.response.prettyPrint();
        customerId= requestBuilder.response.jsonPath().getInt("id");
    }

    @Then("I verify customer is created")
    public void verifyCreateCustomer() throws IOException {
        requestBuilder.getRequestWithPathParam(String.valueOf(customerId), "/customers");
        requestBuilder.response.prettyPrint();
    }
}
