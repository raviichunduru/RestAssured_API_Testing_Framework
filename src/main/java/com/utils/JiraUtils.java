package com.utils;

import enums.ConfigPropertyTypes;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static com.utils.ApiUtils.getStringOutOfJSON;
import static com.utils.PropertyUtils.getValue;
import static constants.FrameworkConstants.REQUEST_JSON_FOLDER_PATH;
import static enums.ConfigPropertyTypes.*;
import static io.restassured.RestAssured.given;

public final class JiraUtils
{
    private JiraUtils () {};

    public static String createIssueInJira (String errormessage)
    {
        String requestBody = getStringOutOfJSON(REQUEST_JSON_FOLDER_PATH + "createIssueInJira.json");
                /*.replace("key","DEM")
                .replace("summary","Logging bug using Rest Assured")
                .replace("description", errormessage);*/

        Response response = given()
                .auth().preemptive().basic(getValue(JIRAUSERNAME), getValue(JIRAPASSWORD))
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                //.header("Authorization", "Basic aXZzX2luZnlAMTQz")
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .post(getValue(JIRAURL));

        response.prettyPrint();
        return response.jsonPath().get("key");

    }
}
