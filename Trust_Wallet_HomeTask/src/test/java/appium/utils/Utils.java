package appium.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.HasClipboard;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utils {

    public static WebElement waitForVisibility(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element not visible after " + timeoutInSeconds + " seconds: " + e.getMessage());
            return null;
        }
    }

    public static boolean isElementNotDisplayed(By by) {
        try {
            WebElement element = AppiumDriverManager.getDriver().findElement(by);
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }


    public static WebElement waitToBeClickable(WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(AppiumDriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String getClipboardContent(AppiumDriver driver) {
        return ((HasClipboard) driver).getClipboardText();
    }

    public static void pressKeyOnAndroidKeyboard(AndroidDriver driver, AndroidKey key)  {
        driver.pressKey(new KeyEvent(key));
    }

    public static void hideKeyboard(AndroidDriver driver)  {
        if(driver.isKeyboardShown()) {   
            driver.hideKeyboard(); 
        }
    }

    public void printPageSource()  {
        AndroidDriver driver = AppiumDriverManager.getDriver();
        String elementHierarchy = driver.getPageSource();
        System.out.println(elementHierarchy);
        try {
            Files.write(Paths.get("PageSource.xml"), elementHierarchy.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String takeScreenshot(String testName, AppiumDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path dest = Paths.get("src/test/java/appium/screenshots", testName + ".png");
        File destFile = dest.toFile();
        try {
            Files.copy(scrFile.toPath(), dest);
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
