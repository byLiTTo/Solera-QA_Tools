package qa.tools.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.tools.constants.TestRailConstants;

public class TestRailRunsPage {
    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private static final String USER_TEXT_FIELD_ID = "name";
    private static final String PASS_TEXT_FIELD_ID = "password";
    private static final String LOGIN_BUTTON_ID = "button_primary";
    private static final String HEADER_ID = "content-header";
    private static final String AUTO_REGRESSION_XPATH_FORMAT = "//div[@class='table']//div[@class='summary-title text-ppp']/a[text()='%s']";
    private static final String AUTO_REGRESSION_CONTENT_ID = "content-inner";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private WebDriver webDriver;
    private WebDriverWait wait;

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    public TestRailRunsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void navigateFirstTime() {
        webDriver.navigate().to(TestRailConstants.TESTRAIL_RUNS_LINK);
        webDriver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_BUTTON_ID)));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void navigate() {
        webDriver.navigate().to(TestRailConstants.TESTRAIL_RUNS_LINK);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HEADER_ID)));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void loginTestRail(String userName, String pass) {
        webDriver.findElement(By.id(USER_TEXT_FIELD_ID)).sendKeys(userName);
        webDriver.findElement(By.id(PASS_TEXT_FIELD_ID)).sendKeys(pass);
        webDriver.findElement(By.id(LOGIN_BUTTON_ID)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(HEADER_ID)));
    }

    public void navigateAutoRegression(String autoRegressionName) {
        webDriver.findElement(By.xpath(String.format(AUTO_REGRESSION_XPATH_FORMAT, autoRegressionName))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(AUTO_REGRESSION_CONTENT_ID)));
    }
}
