package appium.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumDriverManager {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private static final String DEFAULT_DEVICE_NAME = "Pixel 6 API 33";
    private static final String DEFAULT_APP_PATH = System.getProperty("user.dir") + "/src/test/app/TrustWallet_Apr_11_2025.apk";

    public static void createAndroidDriver() {
        try {
            AppiumServerUtility.startServer();

            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName(System.getProperty("deviceName", DEFAULT_DEVICE_NAME))
                    .setAutomationName("UiAutomator2")
                    .setApp(System.getProperty("appPath", DEFAULT_APP_PATH));

            String sURL = AppiumServerUtility.getServiceUrl();
            URL serverUrl = URI.create(sURL).toURL();

            AndroidDriver driver = new AndroidDriver(serverUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            setDriver(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create AndroidDriver due to invalid Appium server URL", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create AndroidDriver", e);
        }
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Please call createAndroidDriver() first.");
        }
        return driver;
    }

    public static void quitSession() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
        AppiumServerUtility.stopServer();
    }

    private static void setDriver(AndroidDriver driver) {
        DRIVER.set(driver);
    }
}