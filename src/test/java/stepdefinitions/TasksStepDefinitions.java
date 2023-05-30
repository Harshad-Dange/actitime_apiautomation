package stepdefinitions;

import com.google.gson.JsonObject;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.List;

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
}
