package qa.tools.controllers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private Driver() {
        throw new IllegalStateException("Driver class");
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static WebDriver configureWebDriver() {
        ChromeOptions co = new ChromeOptions()
                .addArguments("--remote-allow-origins=*")
//                .addArguments("--headless")
//                .addArguments("--disable-gpu")
                .addArguments("--window-size=1920,1080");
        co.setBrowserVersion("114");
        WebDriverManager.chromedriver().setup();
        return new org.openqa.selenium.chrome.ChromeDriver(co);
    }

}
