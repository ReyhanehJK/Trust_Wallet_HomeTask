package appium.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import appium.utils.AppiumDriverManager;

public class SecretPhraseSelectionPage extends Page {

    // Selectors

    public WebElement continueButton() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Continue']")); 
    }

    public WebElement confirmButton() {
        WebElement confrimButton =  AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Confirm']")); 
        return Page.waitToBeClickable(confrimButton, 30);
    }


    // Helpers

    public void selectSecretWords(AppiumDriver driver, String sentence) {
        String[] words = sentence.split(" ");
        
        // Iterate through every third word starting from the first (index 0)
        for (int i = 0; i < words.length; i += 3) {
            String word = words[i];
            try {
                // Try to find and click the word in the UI
                WebElement wordElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + word + "']/ancestor::android.view.View[@clickable='true']"));
                if (wordElement.getAttribute("clickable").equals("true")) {
                    wordElement.click();
                }
            } catch (Exception e) {
                System.out.println("Word not found or could not be clicked: " + word);
            }
        }
    
    }
}
