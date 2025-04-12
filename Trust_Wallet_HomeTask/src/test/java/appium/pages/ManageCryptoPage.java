package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class ManageCryptoPage extends Page {

    // Locators

    public WebElement backButton(){
        return waitForVisibility(AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button"), 30);
    }

    // Helpers

    public WebElement assertManageCryptoPageDisplayed(){
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='All networks']"), 30);
    }

    public void toggleCrypto(String cryptoName){
        WebElement toggle = waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='"+cryptoName+"']/following-sibling::android.view.View"), 30);
        toggle.click();
    }

    public void assertCryptoPresent(String cryptoName, boolean value){
        if(value){
            waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='"+cryptoName+"']"), 30);
        }
        else{
            boolean visibility = isElementNotDisplayed(AppiumBy.xpath("//android.widget.TextView[@text='"+cryptoName+"']"));
            Assert.assertTrue(visibility, "The element is still visible or present, which is not expected.");
        }
    }

    

}

