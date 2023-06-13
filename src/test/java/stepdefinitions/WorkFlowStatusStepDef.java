package stepdefinitions;

import com.github.javafaker.Faker;
import dev.failsafe.Fallback;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojo.CreateWorkFlowResponsePojo;
import pojo.WorkFlowStatusPojo;

import java.util.*;

import static io.restassured.RestAssured.given;

public class WorkFlowStatusStepDef extends BaseClass {

    WorkFlowStatusPojo statusPojo;
    CreateWorkFlowResponsePojo workFlowResponsePojo;

    @Given("I create a workflow status")
    public void createWorkflow(Map<String, String> dataTable) {
        statusPojo = new WorkFlowStatusPojo();
        statusPojo.setName(new Faker().name().firstName());
        statusPojo.setType(dataTable.get("type"));
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(statusPojo)
                .log().all()
                .when().post("/workflowStatuses");
//                .then().assertThat().statusCode(200);


    }

    @Then("I verify workflow status is getting created")
    public void iVerifyWorkflowStatusIsGettingCreated() {
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());

/*
        Deserialization:
        convert response into a class object i.e in CreateWorkFlowResponsePojo class
*/
        workFlowResponsePojo = response.as(CreateWorkFlowResponsePojo.class);
        System.out.println(workFlowResponsePojo.getId());
//        Assert.assertEquals(statusPojo.getName(), response.jsonPath().getString("name") );
//        Assert.assertEquals(statusPojo.getType(), response.jsonPath().getString("type"));

        Assert.assertEquals(statusPojo.getName(), workFlowResponsePojo.getName());
        Assert.assertEquals(statusPojo.getType(), workFlowResponsePojo.getType());

        //verify allowedActions field details
//        Map<String,Boolean> allowedActions=  workFlowResponsePojo.getAllowedActions() ;
        Assert.assertTrue(workFlowResponsePojo.getAllowedActions().get("canModify"));
    }


    @When("I get all workflow statuses")
    public void iGetAllWorkflowStatuses() {
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log().all()
                .when().get("/workflowStatuses");
    }

    @Then("I verify newly created workflow status in the response")
    public void iVerifyNewlyCreatedWorkflowStatusInTheResponse() {

        Assert.assertEquals(200, response.getStatusCode());

        CreateWorkFlowResponsePojo[] obj = response.jsonPath().getObject("items", CreateWorkFlowResponsePojo[].class);
        //print the length of an object
        System.out.println(obj.length);

        boolean flag = false;
        //verify newly created workflow in the response
        for (CreateWorkFlowResponsePojo responsePojo : obj) {
            // check if the get response id is equals with the id of create workflow status response
            if (responsePojo.getId() == workFlowResponsePojo.getId()) {
                flag = true;
            }

        }
        List<Long> ids = new ArrayList<>();
        for (CreateWorkFlowResponsePojo responsePojo : obj) {
            // check if the get response id is equals with the id of create workflow status response
            ids.add(responsePojo.getId());
        }
        Assert.assertTrue(ids.contains(workFlowResponsePojo.getId()));
        Assert.assertTrue(flag);
//            response.as(CreateWorkFlowResponsePojo.class);

    }

    @Given("I hit an api to get all workflowStatuses")
    public void iHitAnApiToGetAllWorkflowStatuses() {
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log().all()
                .when().get("/workflowStatuses");
    }

    @Then("I verify the response is in ascending order by id")
    public void iVerifyTheResponseIsInAscendingOrderById() {

        Assert.assertEquals(200, response.getStatusCode());

//        CreateWorkFlowResponsePojo[] responsePojo = response.jsonPath().getObject("items", CreateWorkFlowResponsePojo[].class);
//        System.out.println(responsePojo);

        List<CreateWorkFlowResponsePojo> actualResponsePojo = response.jsonPath().getList("items",CreateWorkFlowResponsePojo.class);

        // get all ids of workflowstatus
/*
        List<Long> actualIds= new ArrayList<>();
        responsePojo.forEach(obj->{
            actualIds.add(obj.getId());
        });
*/

        List<CreateWorkFlowResponsePojo> expectedResponse= new ArrayList<>();
        expectedResponse.addAll(actualResponsePojo);

        Comparator<CreateWorkFlowResponsePojo> comparator = new Comparator<CreateWorkFlowResponsePojo>(){
            @Override
            public int compare(CreateWorkFlowResponsePojo o1, CreateWorkFlowResponsePojo o2) {
                return (int)o1.getId()-(int)o2.getId();
            }
        };
        //sort the expectedResponse in ascending order
        Collections.sort(expectedResponse,comparator);

        Assert.assertEquals(expectedResponse,actualResponsePojo);

        Assert.assertTrue(expectedResponse.containsAll(actualResponsePojo));


    }
}
