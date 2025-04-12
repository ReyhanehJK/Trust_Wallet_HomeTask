package appium.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

public class WalletsPage extends Page {

    private HomePage home = new HomePage();

    //Helpers

    public WebElement assertWalletHeaderText() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='Wallets']"), 30);
    }

    public WebElement assertWalletAdded(String walletName) {
        home.walletDrowdown(walletName).click();
        assertWalletHeaderText();
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='"+walletName+"']"), 30);
    }
    
}
