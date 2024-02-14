package com.tests;

import annotations.FrameworkAnnotations;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reports.ExtentLogger;
import reports.ExtentManager;
import reports.ExtentReport;
import java.util.concurrent.TimeUnit;
import static requestbuilder.RequestBuilder.buildRequestForGetCalls;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetTests
{
    @FrameworkAnnotations(Authors = {"Ravi","Raju"}, Categories = {"Smoke", "Regression"})
    @Test
    public void getEmployeeDetails()
    {
        ExtentLogger.Info("Fetching All employee details");
        Response response  = buildRequestForGetCalls().get("/employees");
        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.timeIn(TimeUnit.MILLISECONDS)).isLessThan(1000);
        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .isGreaterThan(10);
    }

    @FrameworkAnnotations(Authors = {"Ravi","Raju"}, Categories = {"Smoke", "Regression"})
    @Test
    public void getSpecificEmployeeDetails()
    {
        ExtentLogger.Info("Fetching employee details with ID 1247");
        Response response  = buildRequestForGetCalls()
                .pathParams("id",1247)
                .get("/employees/{id}");

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.timeIn(TimeUnit.MILLISECONDS)).isLessThan(1000);
        assertThat(response.jsonPath().getString("first_name")).isEqualTo("clifford");
    }

    @FrameworkAnnotations(Authors = {"Ravi","Raju"}, Categories = {"Smoke", "Regression"})
    @Test(dataProvider = "getData")
    public void getEmployeeDetailsUsingDataProvider(Integer id, String firstname)
    {
        ExtentLogger.Info("Fetching employee details for : " +id);
        Response response  = buildRequestForGetCalls()
                .pathParams("id",id)
                .get("/employees/{id}");

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.timeIn(TimeUnit.MILLISECONDS)).isLessThan(1000);
        assertThat(response.jsonPath().getString("firstName")).isEqualTo(firstname);
    }

    @DataProvider
    public Object [][] getData()
    {
        return new Object [][]
                { {600, "fn"}, {601, "fn1"}, {500, "fn"}};
    }
}
