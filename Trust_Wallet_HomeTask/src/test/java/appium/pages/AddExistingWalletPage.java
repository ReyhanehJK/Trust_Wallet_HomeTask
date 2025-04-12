package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;


public class AddExistingWalletPage extends Page {

    // Locators

    public WebElement scretPhraseButton(){
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Secret phrase']"));
    }

    // Helpers

}

