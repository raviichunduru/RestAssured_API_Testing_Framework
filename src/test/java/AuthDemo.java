import com.utils.ApiUtils;
import constants.FrameworkConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.utils.ApiUtils.getStringOutOfJSON;
import static constants.FrameworkConstants.REQUEST_JSON_FOLDER_PATH;
import static io.restassured.RestAssured.given;

public class AuthDemo
{
    @Test
    public void basicAuthTest()
    {
        Response response  = given()
                .auth()
                .basic("postman","password")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();
    }

    @Test
    public void getAllWorkspaces ()
    {
        Response response = given()
                .header("X-API-Key", "PMAK-65bbc96a948c1a0001f4f3cf-cd265c9ac98089e982ec8b831459de8a48")
                .log()
                .all()
                .get("https://api.getpostman.com/collections");

        response.prettyPrint();
    }

    @Test
    public void getGitHubRepositories ()
    {
        given().header("Authorization", "Bearer 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")
                .header("Accept", "application/vnd.github+json")
                .queryParam("visibility","private")
                .queryParam("sort", "created")
                .log()
                .all()
                .get("https://api.github.com/user/repos")
                .prettyPrint();
    }
}
