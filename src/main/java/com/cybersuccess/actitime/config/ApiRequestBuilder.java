package com.cybersuccess.actitime.config;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pojo.CustomerPojo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ApiRequestBuilder {

    public RequestSpecification requestSpecification;

    public Response response;

    public void setRequestStructure() throws IOException {
        PropertyHandler property = new PropertyHandler("config.properties");
        requestSpecification.given().relaxedHTTPSValidation()
                .baseUri(property.getProperty("baseUri"))
                .basePath(property.getProperty("basePath"))
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", property.getProperty("token"))
                .log().all();

    }

    public void execute(Method method, String endPoint){
        switch (method){
            case GET:
               response= requestSpecification.get(endPoint);
               break;
            case POST:
                response= requestSpecification.post(endPoint);
                break;
            case PUT:
                response= requestSpecification.put(endPoint);
                break;
            case PATCH:
                response= requestSpecification.patch(endPoint);
                break;
            case DELETE:
                response= requestSpecification.delete(endPoint);
                break;
        }
    }

    public void setQueryParams(Map<String, Object> queryParams){
        Optional.ofNullable(queryParams).ifPresent(params->requestSpecification.queryParams(params));
    }

    public void setRequestBody(String body){
        Optional.ofNullable(body).ifPresent(obj-> requestSpecification.body(obj));

    }
    public <T> void setRequestBody(Class<T> classObject){
        Optional.ofNullable(classObject).ifPresent(obj-> requestSpecification.body(obj));

    }
    public  void setRequestBody(Map<String,Object> body){
        Optional.ofNullable(body).ifPresent(obj-> requestSpecification.body(obj));
    }

    public JSONObject setRequestBodyWithFile(String filePath){
        JSONObject jsonObject = null;
        if(Objects.nonNull(filePath) && !filePath.isEmpty()){
            JSONParser jsonParser= new JSONParser();
            FileReader reader;
            byte[] payload;
            try {
                reader = new FileReader(filePath);
                Object object = jsonParser.parse(reader);
                jsonObject = (JSONObject)object;
                payload = Files.readAllBytes(Path.of(filePath)); // access the content from json file convert into byte array
                requestSpecification.body(payload);
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonObject;
    }
    public void postRequest(String endPoint) throws IOException {
        setRequestStructure();
        execute(Method.POST,endPoint);
    }
}
