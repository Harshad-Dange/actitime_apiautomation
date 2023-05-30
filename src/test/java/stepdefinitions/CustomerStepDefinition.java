package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.util.*;

public class CustomerStepDefinition extends BaseClass {

    private int customerId;

    @Given("I set up the request structure")
    public void setup(Map<String, Object> queryParams) {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
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
            } else if (Objects.nonNull(customerId)) {
                pathParam = String.valueOf(customerId);
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");

            } else {
                response = requestSpecification.get("/" + data.get("endPoint"));

            }
        } else if (data.get("method").equals("POST")) {
            response = requestSpecification.post("/" + data.get("endPoint"));

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

        String reqBody = "{\n" +
                "    \"name\": \"" + customerName + "\",\n" +
                "    \"archived\": false\n" +
                "}";

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
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

           String actualName=  response.jsonPath().getString("name");

           //verify customer name from the response
           Assert.assertEquals(expectedName ,actualName);

           //verify archived field  value and it should be false
           Assert.assertFalse(response.jsonPath().getBoolean("archived"));

            //verify description should be null
//        Assert.assertTrue(response.jsonPath().get("description") == null);

        Assert.assertTrue(Objects.isNull(response.jsonPath().get("description")));
    }

    @When("I set up the request structure get customer information")
    public void iSetUpTheRequestStructureGetCustomerInformation() {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
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
}
