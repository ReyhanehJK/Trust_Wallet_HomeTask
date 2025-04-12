package appium.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServerUtility {

    private static AppiumDriverLocalService service;

    public static void startServer() {
        try {
            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

            /*
            builder.usingDriverExecutable(new File("/usr/local/bin/node"));
            builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));
            */

            service = AppiumDriverLocalService.buildService(builder);
            service.start();

            if (service == null || !service.isRunning()) {
                throw new RuntimeException("Appium server failed to start!");
            }

            System.out.println("✅ Appium server started at: " + service.getUrl());

        } catch (Exception e) {
            throw new RuntimeException("❌ Error while starting Appium server", e);
        }
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("🛑 Appium server stopped.");
        } else {
            System.out.println("⚠️ Appium server was not running.");
        }
    }

    public static String getServiceUrl() {
        if (service != null && service.isRunning()) {
            return service.getUrl().toString();
        }
        throw new IllegalStateException("Appium server is not running.");
    }
}
