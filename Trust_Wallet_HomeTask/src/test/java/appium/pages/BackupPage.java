package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;


public class BackupPage extends Page {

    // Locators

    public WebElement backUpManuallyButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Back up manually']"));
    }

    public WebElement backUpSkipButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='SKIP']"));
    }

    // Helpers

}

