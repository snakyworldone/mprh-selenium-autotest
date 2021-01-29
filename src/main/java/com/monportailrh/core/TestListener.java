package com.monportailrh.core;


import com.monportailrh.utilities.AllureLogger;
import com.monportailrh.utilities.Utilities;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private String testName;
    private String testMethodName;

    @Override
    public void onTestStart(ITestResult result) {
        this.testMethodName = result.getMethod().getMethodName();
        AllureLogger.logToAllure("Starting [" + testMethodName + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureLogger.logToAllure("Test [" + testMethodName + "] passed");
        AllureLogger.logToAllure("");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Utilities utilities = new Utilities();
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("webDriver");
        utilities.saveScreenshot(driver);

        AllureLogger.logToAllure("Test [" + testMethodName + "] failed");
        AllureLogger.logToAllure("Screenshot captured");
        AllureLogger.logToAllure("ASSERTION ERROR: " + result.getThrowable());
        AllureLogger.logToAllure("");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        this.testName = context.getCurrentXmlTest().getName();
        AllureLogger.logToAllure("TEST [" + testName + "] STARTED");
    }

    @Override
    public void onFinish(ITestContext context) {
        AllureLogger.logToAllure("ALL [" + testName + "] FINISHED");
        AllureLogger.logToAllure("");
    }
}