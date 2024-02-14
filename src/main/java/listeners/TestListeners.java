package listeners;

import annotations.FrameworkAnnotations;
import com.utils.JiraUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentLogger;
import reports.ExtentManager;
import reports.ExtentReport;

import static com.utils.JiraUtils.createIssueInJira;

public final class TestListeners implements ITestListener, ISuiteListener
{
    @Override
    public  void onTestStart(ITestResult result)
    {
        ExtentReport.createTest(result.getName());
        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).Authors();
        String[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).Categories();

        ExtentReport.addAuthor(authors);
        ExtentReport.addCategories(categories);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        ExtentLogger.Pass(result.getName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        //System.out.println("result.getThrowable() = " + result.getThrowable());
        ExtentLogger.Fail(String.valueOf(result.getThrowable()));
        String IssueCreatedInJira = createIssueInJira(String.valueOf(result.getThrowable()));
        ExtentLogger.Fail("IssueCreatedInJira : " + "http://localhost:8080/browser/"+IssueCreatedInJira);
    }

    @Override
    public void onStart(ISuite suite)
    {
        ExtentReport.initReport();
    }

    @Override
    public void onFinish(ISuite suite)
    {
        ExtentReport.tearDownReport();
    }
}
