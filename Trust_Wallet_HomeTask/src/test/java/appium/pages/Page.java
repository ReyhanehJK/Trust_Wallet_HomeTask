package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.Utils;

public class Page extends Utils {

    // Locators

    public WebElement deviceSecurityCompramisedOkButton() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='OK']"), 30);
    }

    public WebElement allowNotificationsButton() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"), 30);
    }

    // Helpers


    public void closeDeviceSecurityPopUp(){
        deviceSecurityCompramisedOkButton().click();
    }

    public void allowNotifications(){
        allowNotificationsButton().click();
    }

}
