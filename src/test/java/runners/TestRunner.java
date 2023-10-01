package runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

public class TestRunner{
    @CucumberOptions(tags = "@Sanity",
            features = {"features"},
            glue = {"stepDefinitions"},
            plugin = {"html:target/results.html", "message:target/results.ndjson"},
            monochrome = true
    )
    @Test
    public class Run extends AbstractTestNGCucumberTests {
    }
}
