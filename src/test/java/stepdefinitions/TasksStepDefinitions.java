package stepdefinitions;

import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TasksStepDefinitions extends BaseClass{

    @Then("I verify all tasks info in the response")
    public void verifyResponse(){
        //verify the status code of a response
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Then("I verify all tasks for project id {int}")
    public void iVerifyAllTasksForProjectId(int taskId) {
        List<Integer> projectId = response.jsonPath().getList("items.projectId");

        //Assert the execution if project id does not present in the response
        projectId.forEach(id-> Assert.assertEquals(taskId, (int) id));

        //get the name of zeroth index object
       String name = response.jsonPath().getString("items[0].name");
        System.out.println("Name of 0th index task is: "+ name);

        //get the created field value from 0th idex
        System.out.println("Created Value : "+response.jsonPath().getLong("items[0].created"));

        //get the canModify field value from 0th index
        System.out.println("canModify value from AllowedAction: "+response.jsonPath().getBoolean("items[0].allowedActions.canModify"));

//        response.jsonPath().getList("items").get(0);

        //get the oth index object from items list in response
        LinkedHashMap<String,Object> linkedHashMap =response.jsonPath().getJsonObject("items[0]") ;

        //get the id of an object
        System.out.println("Id of an Object : "+(int)linkedHashMap.get("id"));

        //get the allowedActions Object
        System.out.println("Allowed action field value : "+ linkedHashMap.get("allowedActions"));

        //check the allowedActions field values
        System.out.println(linkedHashMap.get("allowedActions").getClass());

        LinkedHashMap<String,Boolean> allowedAction = (LinkedHashMap<String,Boolean>)linkedHashMap.get("allowedActions");

        //print can modify value
        System.out.println(allowedAction.get("canModify"));

        if(allowedAction.get("canModify")){

        }
        //print cabn delete value
        System.out.println(allowedAction.get("canDelete"));
        System.out.println(response.jsonPath().getJsonObject("items[0]").toString());

    }

    @When("I setup the request structure to create task")
    public void iSetupTheRequestStructureToCreateTask(DataTable table) {
        Map<String,String> createTaskBody = table.asMaps().get(0);
        Map<String, String> payload = new HashMap<>();
        payload.putAll(createTaskBody);
        payload.put("projectId", String.valueOf(projectId));


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
                .body(payload)
                .log()
                .all();
    }

    @Then("I verify task is created successfully")
    public void iVerifyTaskIsCreatedSuccessfully(DataTable table) {
        Map<String, String> createTaskBody  = table.asMaps().get(0);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(createTaskBody.get("name"), response.jsonPath().getString("name"));
        Assert.assertEquals(createTaskBody.get("status"), response.jsonPath().getString("status"));
        Assert.assertEquals(Integer.parseInt(createTaskBody.get("typeOfWorkId")), response.jsonPath().getInt("typeOfWorkId"));
        Assert.assertEquals(customerId, response.jsonPath().getInt("customerId"));
        Assert.assertEquals(projectId, response.jsonPath().getInt("projectId"));
        Assert.assertEquals(Integer.parseInt(createTaskBody.get("estimatedTime")), response.jsonPath().getInt("estimatedTime"));
    }
}
