package appium.tests;

import appium.utils.AppiumDriverManager;
import appium.utils.Utils;

import java.net.MalformedURLException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class Base extends Utils {

    @BeforeMethod(alwaysRun = true)
    public void testSetup() throws MalformedURLException {
        AppiumDriverManager.createAndroidDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        AppiumDriverManager.quitSession();
    }

    @AfterMethod // Take screenshot
    public void afterEachTest(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                takeScreenshot(result.getName(), AppiumDriverManager.getDriver());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
