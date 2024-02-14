package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReport
{
    private ExtentReport () {};

    public static ExtentReports extentReport = new ExtentReports();

    public static void initReport ()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter("index.html");
        extentReport.attachReporter(reporter);
    }

    public static void tearDownReport ()
    {
        extentReport.flush();
    }

    public static void createTest(String name)
    {
        ExtentTest test = extentReport.createTest(name);
        ExtentManager.setExtentTest(test);
    }

    public static void addAuthor (String[] authors)
    {
       for(String author : authors)
       {
           ExtentManager.getExtentTest().assignAuthor(author);
       }
    }

    public static void addCategories (String[] categories)
    {
        for(String category : categories)
        {
            ExtentManager.getExtentTest().assignCategory(category);
        }
    }


}
