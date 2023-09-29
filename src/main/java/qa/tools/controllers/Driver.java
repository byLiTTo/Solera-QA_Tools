/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.controllers;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

    private static final String SELENIUM_GRID_URL = "http://localhost:4444/wd/hub";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private Driver() {
        throw new IllegalStateException("Driver class");
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static RemoteWebDriver configureWebDriver(boolean headless) {
        try {
            ChromeOptions options = new ChromeOptions();
            if (!headless) {
                options.addArguments("--headless");
            }
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to Windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--disable-in-process-stack-traces");
            options.addArguments("--disable-logging");
            options.addArguments("--log-level=3");
            options.addArguments("--remote-allow-origins=*");
            return new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
