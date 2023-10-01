package utils;

import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
    public static ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\vTigerReport.html");
    public static Properties prop = new Properties();
    public static ExtentReports extentReports;



    public static ExtentReports attachReport(String projectName) {
        reporter.config().setReportName(projectName);
        reporter.config().setDocumentTitle("Test Automation Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("OS:: ", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version:: ", System.getProperty("java.version"));
        extentReports.setSystemInfo("UserName:: ", System.getProperty("user.name"));
        extentReports.setSystemInfo("Selenium version:: ", "3.141.59");
        return extentReports;
    }
}
