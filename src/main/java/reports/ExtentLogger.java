package reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public final class ExtentLogger
{
    private ExtentLogger () {};

    public static void Pass (String message)
    {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void Fail (String message)
    {
        ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void Info (String message)
    {
        ExtentManager.getExtentTest().info(message);
    }

    public static void logResponse (String response)
    {
        Info("Response details below");
        ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
    }

    public static void logRequest(RequestSpecification  requestSpecification)
    {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);

        Info("Request details below");
        ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));

        Info("Header details below");
        for(Header header: query.getHeaders())
        {
            Info(header.getName() + "  :  " + header.getValue());
        }
    }
}
