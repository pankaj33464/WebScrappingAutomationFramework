package runners;

import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import utils.DriverManager;

import static utils.DriverManager.driver;

@CucumberOptions(tags = "@Sanity",
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        plugin = {"html:target/results.html", "message:target/results.ndjson"}
)
public class TestRunner extends AbstractTestNGCucumberTests{

}
