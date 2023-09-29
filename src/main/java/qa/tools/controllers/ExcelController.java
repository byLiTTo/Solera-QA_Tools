/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.controllers;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.tools.constants.TestRailConstants;
import qa.tools.models.TestRailCase;
import qa.tools.pages.TestRailPlansPage;
import qa.tools.pages.TestRailRunsPage;
import qa.tools.utils.ExcelUtils;

public class ExcelController {

    private static final String FAILED = "Failed";
    private static final String POSTPONED = "Postponed";

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private String userName = "?";
    private String pass = "?";
    private final RemoteWebDriver webdriver;
    private final TestRailRunsPage testRailRunsPage;
    private final TestRailPlansPage testRailPlansPage;

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public ExcelController(boolean headless) {
        webdriver = Driver.configureWebDriver(headless);
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
    public void updateCtfr(String filePath) {
        String environment = "CTFR";
        String mounth = DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now());
        String year = DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now());

        String autoRegressionName = String.format(TestRailConstants.AUTOREGRESSION_NAME_FORMAT, environment, mounth,
                year);

        testRailRunsPage.navigateAutoRegression(autoRegressionName);

        testRailPlansPage.openLastAutoRegression();

        List<TestRailCase> failedCases = testRailPlansPage.loadTestCases(FAILED);

        webdriver.navigate().back();
        testRailPlansPage.openLastAutoRegression();
        List<TestRailCase> actualRegressionPostponed = testRailPlansPage.loadTestCases(POSTPONED);

        Map<String, TestRailCase> previousRegression = ExcelUtils.convertExcel2Hashmap(new File(filePath));
        List<TestRailCase> postponedCases = ExcelUtils.mergePostponedCases(previousRegression,
                actualRegressionPostponed);

        List<TestRailCase> regression = Stream.concat(failedCases.stream(), postponedCases.stream())
                .collect(Collectors.toList());

        ExcelUtils.convertTestCase2Excel(new File(filePath), regression);
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void updateInt1fr(String filePath) {
        String environment = "INT1FR";
        String mounth = DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now());
        String year = DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now());

        String autoRegressionName = String.format(TestRailConstants.AUTOREGRESSION_NAME_FORMAT, environment, mounth,
                year);

        testRailRunsPage.navigateAutoRegression(autoRegressionName);

        testRailPlansPage.openLastAutoRegression();

        List<TestRailCase> failedCases = testRailPlansPage.loadTestCases(FAILED);

        webdriver.navigate().back();
        testRailPlansPage.openLastAutoRegression();
        List<TestRailCase> actualRegressionPostponed = testRailPlansPage.loadTestCases(POSTPONED);

        Map<String, TestRailCase> previousRegression = ExcelUtils.convertExcel2Hashmap(new File(filePath));
        List<TestRailCase> postponedCases = ExcelUtils.mergePostponedCases(previousRegression,
                actualRegressionPostponed);

        List<TestRailCase> regression = Stream.concat(failedCases.stream(), postponedCases.stream())
                .collect(Collectors.toList());

        ExcelUtils.convertTestCase2Excel(new File(filePath), regression);
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void updateCustom(String filePath, String regressionName, String resultsName) {
        testRailRunsPage.navigateAutoRegression(regressionName);

        testRailPlansPage.openCustomAutoRegression(resultsName);

        List<TestRailCase> failedCases = testRailPlansPage.loadTestCases(FAILED);

        webdriver.navigate().back();
        testRailPlansPage.openCustomAutoRegression(resultsName);
        List<TestRailCase> actualRegressionPostponed = testRailPlansPage.loadTestCases(POSTPONED);

        Map<String, TestRailCase> previousRegression = ExcelUtils.convertExcel2Hashmap(new File(filePath));
        List<TestRailCase> postponedCases = ExcelUtils.mergePostponedCases(previousRegression,
                actualRegressionPostponed);

        List<TestRailCase> regression = Stream.concat(failedCases.stream(), postponedCases.stream())
                .collect(Collectors.toList());

        ExcelUtils.convertTestCase2Excel(new File(filePath), regression);
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public void closeBrowser() {
        webdriver.close();
    }
}
