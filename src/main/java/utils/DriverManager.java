package utils;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverManager {
    public static WebDriver driver;

    public static Properties prop = new Properties();

    public static void openBrowser(String browser) throws SessionNotCreatedException {


        {
            try {
                prop.load(Files.newInputStream(Paths.get("./src/test/resources/config/testconfig.properties")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String url = prop.getProperty("url");

        switch (browser) {

            case "chrome":
                driver = new ChromeDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.get(url);
                break;

            case "FF":
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.get(url);
                break;

            case "Safari":
                driver = new SafariDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.get(url);
                break;

            default:
                driver = new EdgeDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.get(url);

        }

    }

    public static void closeBrowser() {
        DriverManager.driver.quit();
        DriverManager.driver.close();
    }
}
