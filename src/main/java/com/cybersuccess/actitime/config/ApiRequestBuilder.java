package com.cybersuccess.actitime.config;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    public RequestSpecification requestSpecification = given();

    public Response response;
    private static ApiRequestBuilder apiRequestBuilder;
    private String pathParam;


    public static ApiRequestBuilder getInstance() {
        if (Objects.isNull(apiRequestBuilder)) {
            apiRequestBuilder = new ApiRequestBuilder();
        }
        return apiRequestBuilder;
    }

    public void setRequestConfig() throws IOException {
        PropertyHandler property = new PropertyHandler("config.properties");
        requestSpecification.relaxedHTTPSValidation()
                .baseUri(property.getProperty("baseUri"))
                .basePath(property.getProperty("basePath"))
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .header("Authorization", property.getProperty("token"))
                .log().all();
    }

    public void execute(Method method, String endPoint) {
        switch (method) {
            case GET:
                response = Objects.isNull(pathParam)
                        ? requestSpecification.get(endPoint)
                        : requestSpecification.get(endPoint+"/"+"{pathParam}");
                break;
            case POST:
                response = Objects.isNull(pathParam)
                        ? requestSpecification.post(endPoint)
                        : requestSpecification.post(endPoint+"/"+"{pathParam}");
                break;
            case PUT:
                response = Objects.isNull(pathParam)
                        ? requestSpecification.put(endPoint)
                        : requestSpecification.put(endPoint+"/"+"{pathParam}");
                break;
            case PATCH:
                response = Objects.isNull(pathParam)
                        ? requestSpecification.patch(endPoint)
                        : requestSpecification.patch(endPoint+"/"+"{pathParam}");
                break;
            case DELETE:
                response = Objects.isNull(pathParam)
                        ? requestSpecification.delete(endPoint)
                        : requestSpecification.delete(endPoint+"/"+"{pathParam}");
                break;
        }
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        Optional.ofNullable(queryParams).ifPresent(params -> requestSpecification.queryParams(params));
    }

    public void setPathParam(String param) {
        Optional.ofNullable(param).ifPresent(p -> {
            pathParam = p;
            requestSpecification.pathParam("pathParam", p);
        });

    }

    public void setRequestBody(String body) {
        Optional.ofNullable(body).ifPresent(obj -> requestSpecification.body(obj));

    }

    public <T> void setRequestBody(T classObject) {
        Optional.ofNullable(classObject).ifPresent(obj -> requestSpecification.body(obj));

    }

    public void setRequestBody(Map<String, Object> body) {
        Optional.ofNullable(body).ifPresent(obj -> requestSpecification.body(obj));
    }

    public JSONObject setRequestBodyWithFile(String filePath) {
        JSONObject jsonObject = null;
        if (Objects.nonNull(filePath) && !filePath.isEmpty()) {
            JSONParser jsonParser = new JSONParser();
            FileReader reader;
            byte[] payload;
            try {
                reader = new FileReader(filePath);
                Object object = jsonParser.parse(reader);
                jsonObject = (JSONObject) object;
                payload = Files.readAllBytes(Path.of(filePath)); // access the content from json file convert into byte array
                requestSpecification.body(payload);
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonObject;
    }

    public <T> void postRequest(T clazz, String endPoint) throws IOException {
        setRequestConfig();
        setRequestBody(clazz);
        execute(Method.POST, endPoint);
    }

    public void getRequestWithQueryParam(Map<String, Object> queryParams, String endPoint) throws IOException {
        setRequestConfig();
        setQueryParams(queryParams);
        execute(Method.GET, endPoint);
    }

    public void getRequestWithPathParam(String param, String endPoint) throws IOException {
        setRequestConfig();
        setPathParam(param);
        execute(Method.GET, endPoint);
    }
}
