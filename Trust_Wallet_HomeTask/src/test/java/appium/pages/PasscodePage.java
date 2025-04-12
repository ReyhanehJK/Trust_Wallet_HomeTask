package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;

public class PasscodePage extends Page {

    //Helpers

    public WebElement assertPasscodeScreenDisplayed() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='Create passcode']"), 30);
    }

    public void enterPasscode(String [] digits) {
        for (String digit : digits) {
            WebElement digitButton = AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + digit + "']"));
            digitButton.click();
        }
    }
}
