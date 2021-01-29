package com.monportailrh.core;

import com.monportailrh.object.BasePageObject;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utilities.AllureLogger;
import org.openqa.selenium.WebDriver;

public class BaseRouter extends BasePageObject {

    public BaseRouter(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPage(String url) {
        AllureLogger.logToAllure("Opening page: " + url);
        openUrl(url);
        AllureLogger.logToAllure("Page opened!");
        return new LoginPage(driver);
    }
}
