import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Test {



    public static void main (String [] args) throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        WebDriver driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.name("q")).sendKeys("Download Selenium");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@role='presentation']/span[text()='download selenium'])[1]")).click();

        Thread.sleep(3000);
        driver.quit();




    }
}
