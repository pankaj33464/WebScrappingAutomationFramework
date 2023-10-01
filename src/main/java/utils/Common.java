package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Common {
    public static void takeScreenshot(String screenShotName){
        TakesScreenshot screenshot=(TakesScreenshot) DriverManager.driver;
        File file =screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(".\\reports\\" + screenShotName + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
