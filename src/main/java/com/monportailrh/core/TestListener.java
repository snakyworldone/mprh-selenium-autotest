package com.monportailrh.core;


import com.monportailrh.utility.AllureLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    String testName;
    String testMethodName;

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
        AllureLogger.logToAllure("Test [" + testMethodName + "] failed");
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
