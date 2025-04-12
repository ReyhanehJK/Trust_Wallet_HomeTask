package appium.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import com.google.common.collect.ImmutableList;
import appium.utils.AppiumDriverManager;

public class HomePage extends Page {

    // Locators

    public WebElement newUserHomeHeaderText() {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='Join 70M+ people shaping the future of the internet with us']"), 30);
        
    }

    public WebElement getStartedButton() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']"));
    }

    public WebElement cryptoTab() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Crypto']"));
    }

    public WebElement startUsingTrustWalletButton() {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Start using Trust Wallet']"));

    }

    public WebElement walletDrowdown(String walletName) {
        return AppiumDriverManager.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+walletName+"']"));
    }
   
    // Helpers

    public void assertNewUserHomePageDisplayed(){
        assert newUserHomeHeaderText().isDisplayed();
    }

    public WebElement walletDisplayed(String walletName) {
        return waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='"+walletName+"']"), 30);
    }

    public void clickManageCryptoButton() throws InterruptedException {
        cryptoTab().click();
        scroll("UP", 0.5);
        Thread.sleep(2000);
        WebElement manageCryptoButton =  waitForVisibility(AppiumBy.xpath("//android.widget.TextView[@text='Manage crypto']"), 30);
        manageCryptoButton.click();
    }

    public static void scroll(String direction, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = AppiumDriverManager.getDriver().manage().window().getSize();
        System.out.println(size);
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
        int top = midPoint.y - (int) (midPoint.y * scrollRatio);

        if (direction == "UP") {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), Duration.ofMillis(500));
        } else {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), Duration.ofMillis(500));
        }
    }

    protected static void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) AppiumDriverManager.getDriver()).perform(ImmutableList.of(swipe));
    }


}
