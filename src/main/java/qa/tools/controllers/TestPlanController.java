package qa.tools.controllers;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.tools.models.TestRailCase;
import qa.tools.pages.TestRailPlansPage;
import qa.tools.pages.TestRailRunsPage;

public class TestPlanController {

    private final RemoteWebDriver webdriver;
    private final TestRailRunsPage testRailRunsPage;
    private final TestRailPlansPage testRailPlansPage;
    private String userName = "?";
    private String pass = "?";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestPlanController() {
        webdriver = Driver.configureWebDriver();
        testRailRunsPage = new TestRailRunsPage(webdriver);
        testRailPlansPage = new TestRailPlansPage(webdriver);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }

    public RemoteWebDriver getWebdriver() {
        return webdriver;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void openTestRailRunsWithLogin() {
        testRailRunsPage.navigateFirstTime();
        testRailRunsPage.loginTestRail(userName, pass);
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void openTestRailRuns() {
        testRailRunsPage.navigate();
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void updateTestPlan(String regressionName, String resultsName, String testplanName) {
        testRailRunsPage.navigateAutoRegression(regressionName);

        testRailPlansPage.openCustomAutoRegression(resultsName);

        List<String> passed = testRailPlansPage.loadTestCasesID("Passed");

        webdriver.navigate().back();
        webdriver.navigate().back();
        testRailRunsPage.navigateAutoRegression(testplanName);
        testRailPlansPage.openTestPlan();

        testRailPlansPage.updatePassedTestCases(passed);

        closeBrowser();
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void closeBrowser() {
        webdriver.close();
    }

}
