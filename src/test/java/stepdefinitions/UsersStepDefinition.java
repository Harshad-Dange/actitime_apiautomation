package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import pojo.UserTypes;
import pojo.UsersGoRestPojo;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class UsersStepDefinition extends BaseClass {

    JSONObject createUserPayload;
    UserTypes userTypes;
    UsersGoRestPojo goRestPojo;


    @Given("I setup the request structure to create user")
    public void createUser() {
        createUserPayload = new JSONObject();
        String firstName = new Faker().name().firstName();
        createUserPayload.put("email", firstName + "@yopmail.com");
        createUserPayload.put("firstName", firstName);
        createUserPayload.put("lastName", new Faker().name().lastName());
        createUserPayload.put("username", firstName);
        createUserPayload.put("password", "12345!@#$%");

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
                .body(createUserPayload.toString())
                .log()
                .all();


    }

    @Then("I verify user is getting created successfully")
    public void iVerifyUserIsGettingCreatedSuccessfully() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(createUserPayload.get("firstName"), response.jsonPath().getString("firstName"));
        Assert.assertTrue(Objects.isNull(response.jsonPath().getString("password")));
    }

    @Given("I setup the request structure to create user using json file")
    public void iSetupTheRequestStructureToCreateUserUsingJsonFile() throws IOException, ParseException {

        String jsonFIlePath = "src/test/resources/CreateUserPayload.json";

        //code to read the json content of the file
        JSONParser jsonParser= new JSONParser();
        //read the json file
        FileReader reader= new FileReader(jsonFIlePath);
        //parse the json file content
        Object object = jsonParser.parse(reader);
        //convert object into JSONObject
//        JSONObject jsonObject = (JSONObject)object;

        createUserPayload = (JSONObject)object;

        System.out.println(createUserPayload);


        byte[] payload;
        try {
            payload = Files.readAllBytes(Path.of(jsonFIlePath)); // access the content from json file convert into byte array
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @Given("I setup the request structure to create user using class object")
    public void iSetupTheRequestStructureToCreateUserUsingClassObject() {
        String firstName = new Faker().name().firstName();
        userTypes = new UserTypes();
        userTypes.setFirstName(firstName);
        userTypes.setLastName(new Faker().name().lastName());
        userTypes.setUsername(firstName);
        userTypes.setEmail(firstName + "@gmail.com");
        userTypes.setPassword("1234556");


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
                .body(userTypes)
                .log()
                .all();

    }

    @Then("I verify user is getting created successfully using class object")
    public void iVerifyUserIsGettingCreatedSuccessfullyUsingClassObject() {

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(userTypes.getFirstName(), response.jsonPath().getString("firstName"));
        // rest all assertion will be in same way.....
    }

    @Given("I setup the request structure to create user in gorest")
    public void iSetupTheRequestStructureToCreateUser(DataTable table) {
        Map<String, String> dataTable = table.asMaps().get(0);
        String name = new Faker().name().fullName();
        goRestPojo = new UsersGoRestPojo();
        goRestPojo.setName(name);
        goRestPojo.setEmail(name.replace(" ", ".") + "@gmail.com");
        goRestPojo.setGender(dataTable.get("gender"));
        goRestPojo.setStatus(dataTable.get("status"));
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in")
                .basePath("/public/v2")
                .header("Authorization", "Bearer 526f4b67bf691be98e94abe1df982518410da2dd4e2250823f06bff490e19f12")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(goRestPojo)
                .log()
                .all();
    }

    @Then("I verify user api response with status code {int}")
    public void iVerifyUserIsGettingCreatedSuccessfullyInGotrest(int expStatusCode) {
        Assert.assertEquals(expStatusCode, response.getStatusCode());
        Assert.assertEquals(goRestPojo.getName(), response.jsonPath().getString("name"));
        Assert.assertEquals(goRestPojo.getEmail(), response.jsonPath().getString("email"));
        Assert.assertEquals(goRestPojo.getStatus(), response.jsonPath().getString("status"));
        Assert.assertEquals(goRestPojo.getGender(), response.jsonPath().getString("gender"));
        userId= response.jsonPath().getInt("id");
    }

    @And("I update the newly created user")
    public void iUpdateTheNewlyCreatedUser(DataTable table) {
        Map<String, String> dataTable = table.asMaps().get(0);
        goRestPojo.setStatus(dataTable.get("status"));
        goRestPojo.setGender(dataTable.get("gender"));
        if (dataTable.get("name").equals("random")) {
            goRestPojo.setName(new Faker().name().fullName());
        } else if (dataTable.get("email").equals("random")) {
            goRestPojo.setEmail(new Faker().name().firstName()+ "@gmail.com");
        }
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in")
                .basePath("/public/v2")
                .header("Authorization", "Bearer 526f4b67bf691be98e94abe1df982518410da2dd4e2250823f06bff490e19f12")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(goRestPojo)
                .log()
                .all();

        if(dataTable.get("pathParam").equals("userId")){
            requestSpecification.pathParam("goRestUserId", userId);
        }
    }

}
