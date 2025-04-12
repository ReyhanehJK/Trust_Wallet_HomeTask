package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;

public class SecretPhraseImportancePage extends Page {

    // Selectors

    public WebElement secretPhraseImportancePageHeader() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='This secret phrase is the master key to your wallet']"), 30);
    }

    public WebElement continueButton() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")); 
    }

    // Helpers

    // Method to verify checkboxes are present and check them
    public void verifyAndCheckAllCheckboxes() {
        String[] checkboxTexts = {
            "Trust Wallet does not keep a copy of your secret phrase.",
            "Saving this digitally in plain text is NOT recommended. Examples include screenshots, text files, or emailing yourself",
            "Write down your secret phrase, and store it in a secure offline location!"
        };

        for (String text : checkboxTexts) {
            WebElement checkbox = AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + text + "']"));
            if (checkbox.isDisplayed()) {
                System.out.println("Checkbox with text \"" + text + "\" is present.");
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            } else {
                throw new AssertionError("Checkbox with text \"" + text + "\" is not present.");
            }
        }
    }
}
