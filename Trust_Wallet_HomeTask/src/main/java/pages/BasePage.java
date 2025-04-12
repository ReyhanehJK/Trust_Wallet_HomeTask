package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import appium.utils.AppiumDriverManager;

public class BasePage {

    protected AppiumDriver driver;

    public BasePage() {
        this.driver = AppiumDriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
