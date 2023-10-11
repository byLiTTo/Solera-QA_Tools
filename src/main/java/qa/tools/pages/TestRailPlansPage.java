/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.tools.models.TestRailCase;

public class TestRailPlansPage {

    private static final Logger logger = Logger.getLogger(TestRailPlansPage.class.getName());

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private static final String LAST_AUTO_REGRESSION_XPATH =
            "//div[@class='detailsContainer'][last()]//a[contains(text(),'QapterClaims FR')]";
    private static final String CUSTOM_AUTO_REGRESSION_XPATH_FORMAT =
            "//div[@class='detailsContainer']//a[contains(text(),'%s')]";
    private static final String GRID_CHART_ID = "grid-chart-14959939";
    private static final String TESTCASES_TYPE_XPATH_FORMAT = "//td/a[contains(text(),'%s')]/../..";
    private static final String TESTCASES_AUTOMATED_FAILED_XPATH = "//td/a[contains(text(),'Failed')]/../../td[@class='sub' and contains(text(),'Automated')]/..";
    private static final String TESTCASES_MAINTENANCE_XPATH = "//td[@class='sub' and contains(text(),'Maintenance')]/..";
    private static final String TESTCASES_STATUS_BUTTON_XPATH_FORMAT =
            TESTCASES_TYPE_XPATH_FORMAT + "//a[@class='dropdownLink status hidden-xs']";
    private static final String TABLE_TITLES_XPATH = "//*[@id=\"grid-14959939\"]/tbody/tr//a";
    private static final String DISPLAYER = "./td[@class='action']";
    private static final String HISTORY_TAB = "//div[@class='tab-header']/a[@id= 'historyTab']";
    private static final String HISTORY_TAB_ICON =
            HISTORY_TAB + "/div[@class='link-tooltip tab-header-help-link tooltip-help-link']";
    private static final String HISTORY_RECENTLY = "//div[@id='history']";
    private static final String HISTORY_RECENTLY_TEST_XPATH_FORMAT =
            HISTORY_RECENTLY + "//div[@class='chart-legend-name text-ppp' and contains(text(),'%s')]";
    private static final String ADD_RESULT_POP_UP_ID = "addResultDialog";
    private static final String ADD_RESULT_BUTTON_ID = "addResultSubmit";
    private static final String CANCEL_RESULT_BUTTON_ID = "addResultClose";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private static final String SCROLL_SCRIPT = "arguments[0].scrollTop = arguments[0].scrollHeight";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailPlansPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private void sleep(int timemillis) {
        try {
            Thread.sleep(timemillis);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private String loadRecentlyHistory(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript(SCROLL_SCRIPT, element.findElement(By.xpath(DISPLAYER)));
        sleep(500);

        wait.until(ExpectedConditions.visibilityOf(element.findElement(By.xpath(DISPLAYER))));
        wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.xpath(DISPLAYER)))).click();

        wait.until(ExpectedConditions.attributeContains(
                element.findElement(By.xpath(DISPLAYER + "//span[@class='action-collapse hidden']")), "style",
                "display: inline;"));

        js.executeScript(SCROLL_SCRIPT, webDriver.findElement(By.xpath(HISTORY_TAB)));
        sleep(500);

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(HISTORY_TAB))));
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath(HISTORY_TAB)))).click();
        wait.until(ExpectedConditions.attributeContains(webDriver.findElement(By.xpath(HISTORY_TAB_ICON)), "style",
                "display: block;"));
        sleep(500);

        js.executeScript(SCROLL_SCRIPT,
                webDriver.findElement(By.xpath(String.format(HISTORY_RECENTLY_TEST_XPATH_FORMAT, "Failed"))));

        String totalPassed = webDriver.findElement(
                By.xpath(String.format(HISTORY_RECENTLY_TEST_XPATH_FORMAT, "Passed"))).getText().split(" ")[0];
        String totalFail = webDriver.findElement(By.xpath(String.format(HISTORY_RECENTLY_TEST_XPATH_FORMAT, "Failed")))
                .getText().split(" ")[0];
        String totalPostponed = webDriver.findElement(
                        By.xpath(String.format(HISTORY_RECENTLY_TEST_XPATH_FORMAT, "Postponed"))).getText()
                .split(" ")[0];

        return totalFail + "/" + totalPassed + "/" + totalPostponed;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void openLastAutoRegression() {
        webDriver.findElement(By.xpath(LAST_AUTO_REGRESSION_XPATH)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GRID_CHART_ID)));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void openCustomAutoRegression(String regressionID) {
        webDriver.findElement(By.xpath(String.format(CUSTOM_AUTO_REGRESSION_XPATH_FORMAT, regressionID))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GRID_CHART_ID)));
    }

    public void openTestPlan() {
        webDriver.findElement(By.xpath(String.format(CUSTOM_AUTO_REGRESSION_XPATH_FORMAT, "QapterClaims FR"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GRID_CHART_ID)));
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public List<TestRailCase> loadTestCases(String type) {
        sleep(1000);

        int checkAndStart = 2;
        int titleIndex = 0;
        int caseIDIndex = 0;

        List<WebElement> titlesTable = webDriver.findElements(By.xpath(TABLE_TITLES_XPATH));
        for (int i = 0; i < titlesTable.size(); i++) {
            if (titlesTable.get(i).getAttribute("title").equals("Title")) {
                titleIndex = i + checkAndStart;
            }
            if (titlesTable.get(i).getAttribute("title").equals("Case ID")) {
                caseIDIndex = i + checkAndStart;
            }
        }
        List<WebElement> testCasesElement;
        if (type.equals("Failed")) {
            testCasesElement = webDriver.findElements(By.xpath(TESTCASES_AUTOMATED_FAILED_XPATH));
        } else {
            testCasesElement = webDriver.findElements(By.xpath(TESTCASES_MAINTENANCE_XPATH));
        }

        List<TestRailCase> temp = new ArrayList<>();
        for (WebElement element : testCasesElement) {
            WebElement tmp = element.findElement(By.xpath("./td[" + titleIndex + "]/a"));
            String titleHyperlinkAddress = tmp.getAttribute("href");
            String titleHyperlinkLabel = tmp.getText();

            tmp = element.findElement(By.xpath("./td[" + caseIDIndex + "]/a"));
            String caseHyperlinkAddress = tmp.getAttribute("href");
            String caseHyperlinkLabel = tmp.getText();

            String testStatus = element.findElement(By.xpath("./td/a[@class='dropdownLink status hidden-xs']"))
                    .getText();

            String section = element.findElement(By.xpath("./../../..//*[@class='title pull-left']")).getText();

            temp.add(new TestRailCase(
                    new String[]{titleHyperlinkAddress, titleHyperlinkLabel},
                    new String[]{caseHyperlinkAddress, caseHyperlinkLabel},
                    testStatus,
                    section
            ));
        }
        return temp;
    }

    public List<String> loadTestCasesID(String type) {
        sleep(1000);

        int checkAndStart = 2;
        int caseIDIndex = 0;

        List<WebElement> titlesTable = webDriver.findElements(By.xpath(TABLE_TITLES_XPATH));
        for (int i = 0; i < titlesTable.size(); i++) {
            if (titlesTable.get(i).getAttribute("title").equals("Case ID")) {
                caseIDIndex = i + checkAndStart;
            }
        }

        List<WebElement> testCasesElement = webDriver.findElements(
                By.xpath(String.format(TESTCASES_TYPE_XPATH_FORMAT, type))
        );

        List<String> temp = new ArrayList<>();
        for (WebElement element : testCasesElement) {
            temp.add(element.findElement(By.xpath("./td[" + caseIDIndex + "]/a")).getText());
        }
        return temp;
    }

    public void updatePassedTestCases(List<String> cases) {
        for (String title : cases) {
            try {
                WebElement testCase = webDriver.findElement(
                        By.xpath(String.format(TESTCASES_STATUS_BUTTON_XPATH_FORMAT, title)));

                System.out.println(title);

                JavascriptExecutor js = (JavascriptExecutor) webDriver;

                js.executeScript(SCROLL_SCRIPT, testCase);
                sleep(500);

                wait.until(ExpectedConditions.visibilityOf(testCase));
                wait.until(ExpectedConditions.elementToBeClickable(testCase)).click();

                wait.until(ExpectedConditions.visibilityOf(
                        testCase.findElement(By.xpath("//a[@class='dropdown-menu-link' and text()='Passed']"))));
                wait.until(ExpectedConditions.elementToBeClickable(
                                testCase.findElement(By.xpath("//a[@class='dropdown-menu-link' and text()='Passed']"))))
                        .click();

                wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id(ADD_RESULT_BUTTON_ID))));
                wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id(ADD_RESULT_BUTTON_ID))))
                        .click();

                wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.id(ADD_RESULT_POP_UP_ID))));
            } catch (NoSuchElementException exception) {
                System.out.println("null");
            }
        }
    }
}
