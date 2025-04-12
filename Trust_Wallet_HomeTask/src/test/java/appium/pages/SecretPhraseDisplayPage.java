package appium.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;

public class SecretPhraseDisplayPage extends Page {

    AndroidDriver driver = AppiumDriverManager.getDriver();

    // Selectors

    public WebElement continueButton() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")); 
    }


    // Helpers


    // Method to verify 12 secret words are displayed
    public void verifySecretPhraseWordsDisplayed() {
        for (int i = 1; i <= 12; i++) {
            String expectedText = i + ".";
            WebElement textElement = AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[starts-with(@text, '" + expectedText + "')]"));
            assert textElement.isDisplayed() : "Text with '" + expectedText + "' is not displayed.";
        }
    }

    public void clickCopyToClipboardButton() {
        WebElement copyButton = AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Copy to Clipboard']"));
        copyButton.click();
    }
    
}
