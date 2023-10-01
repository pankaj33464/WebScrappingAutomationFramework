package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverManager;

public class BaseTest {
    public static final String Browser = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        DriverManager.openBrowser(Browser);
    }

    @After
    public void tearDown() {
        DriverManager.closeBrowser();

    }
}