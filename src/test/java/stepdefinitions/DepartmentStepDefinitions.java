package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DepartmentStepDefinitions extends BaseClass {

    @Given("I setup the request structure to get all department")
    public void getAllDepartment() {
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

    @Then("I verify get all department details")
    public void iVerifyGetAllDepartmentDetails() {

        List<Map<String, Object>> itemsList = response.jsonPath().getList("items");

        List<Integer> ids = response.jsonPath().getList("items.id");

        Optional.ofNullable(ids).ifPresent(list -> {

            Collections.sort(list);

            System.out.println(list);

            list.forEach(id -> itemsList.forEach(map -> {
                if (map.get("id").equals(id)) {
                    System.out.println("Id: " + id + " Name: " + map.get("name"));
                }
            }));
        });


    }
}
