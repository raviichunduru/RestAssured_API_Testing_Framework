package com.tests;

import annotations.FrameworkAnnotations;
import com.utils.RandomUtils;
import constants.FrameworkConstants_Singleton;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.Employee;
import reports.ExtentLogger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import static com.utils.ApiUtils.getStringOutOfJSON;
import static com.utils.ApiUtils.writeJsonToFile;
import static constants.FrameworkConstants.REQUEST_JSON_FOLDER_PATH;
import static constants.FrameworkConstants.RESPONSE_JSON_FOLDER_PATH;
import static io.restassured.RestAssured.post;
import static requestbuilder.RequestBuilder.buildRequestForPostCalls;
import static com.utils.RandomUtils.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostTests
{
    @FrameworkAnnotations(Authors = {"Sap","Yash"}, Categories = {"E2E"})
    @Test
    public void postRequestUsingPOJO(Method method)
    {
        // create employee pojo class and use builder pattern to initialize values and post to db

        Employee employee = Employee
                .builder()
                .setID(getID())
                .setFirst_name(getFirstName())
                .setLast_name(getLastName())
                .getEmployee();

        RequestSpecification requestSpecification =buildRequestForPostCalls()
                .body(employee);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        ExtentLogger.logResponse(response.asPrettyString());

        ResponseAssert.
                 assertThat(response).isPostRequestSuccessful()
                .hasHeaderApplicationJson()
                .isResponseTimeWithInLimit();

        /*Employee responseEmployee = response.as(Employee.class);
        EmployeeAssert
                .assertThat(responseEmployee).hasID(employee.getID());*/

        writeJsonToFile(RESPONSE_JSON_FOLDER_PATH + method.getName() +".json",response);

        //validating response schema
        response.then().body(JsonSchemaValidator.matchesJsonSchema(
                new File(System.getProperty("user.dir")+"/src/test/resources/json_schema/schema.json")));
    }

    @FrameworkAnnotations(Authors = {"Sap","Yash"}, Categories = {"E2E"})
    @Test
    public void postRequestUsingExternalFile(Method method)
    {
        String requestString = getStringOutOfJSON(REQUEST_JSON_FOLDER_PATH + method.getName() + ".json")
                .replace("abcd", RandomUtils.getFirstName())
                .replace("115", String.valueOf(RandomUtils.getID()));

        RequestSpecification requestSpecification =buildRequestForPostCalls()
                .body(requestString);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        ExtentLogger.logResponse(response.asPrettyString());

        writeJsonToFile(RESPONSE_JSON_FOLDER_PATH + method.getName() +".json",response);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.timeIn(TimeUnit.MILLISECONDS)).isLessThan(1000);
    }

    @FrameworkAnnotations(Authors = {"Sap","Yash"}, Categories = {"E2E"})
    @Test (description = "to demonstrate usage of singleton pattern")
    public void postRequestUsingExternalFile1(Method method)
    {
        String requestString = getStringOutOfJSON(FrameworkConstants_Singleton.getInstance().getREQUEST_JSON_FOLDER_PATH() + method.getName() + ".json")
                .replace("abcd", RandomUtils.getFirstName())
                .replace("115", String.valueOf(RandomUtils.getID()));

        RequestSpecification requestSpecification =buildRequestForPostCalls()
                .body(requestString);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        ExtentLogger.logResponse(response.asPrettyString());

        writeJsonToFile(FrameworkConstants_Singleton.getInstance().getRESPONSE_JSON_FOLDER_PATH() + method.getName() +".json",response);

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.timeIn(TimeUnit.MILLISECONDS)).isLessThan(1000);
    }
}