/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.controllers;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private Driver() {
        throw new IllegalStateException("Driver class");
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static RemoteWebDriver configureWebDriver() {
//        ChromeOptions co = new ChromeOptions()
//                .addArguments("--remote-allow-origins=*")
////                .addArguments("--headless")
////                .addArguments("--disable-gpu")
//                .addArguments("--window-size=1920,1080");
//        co.setBrowserVersion("114");
//        WebDriverManager.chromiumdriver().setup();
//        return new org.openqa.selenium.chrome.ChromeDriver(co);

//        WebDriverManager.chromiumdriver().setup();
//
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
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
//
//        return new ChromeDriver(options);

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
