package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;


public class SelectNetworkPage extends Page {

    // Locators

    public WebElement selectNetwork(String networkName){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+networkName+"']"));
    }

    // Helpers

}

