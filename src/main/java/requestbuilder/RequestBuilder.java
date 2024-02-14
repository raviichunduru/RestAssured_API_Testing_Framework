package requestbuilder;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.utils.PropertyUtils.getValue;
import static enums.ConfigPropertyTypes.BASEURL;
import static io.restassured.RestAssured.given;

public final class RequestBuilder
{
    private RequestBuilder() {};

    public static RequestSpecification buildRequestForGetCalls()
    {
        return given()
                .log()
                .all()
                .baseUri(getValue(BASEURL));
    }

    public static RequestSpecification buildRequestForPostCalls()
    {
        return  given()
                .baseUri(getValue(BASEURL))
                .header("Content-Type", ContentType.JSON)
                .log()
                .all();
    }
}
