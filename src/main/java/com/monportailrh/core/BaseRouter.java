package com.monportailrh.core;

import com.monportailrh.object.BasePageObject;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utilities.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BaseRouter extends BasePageObject {

    public BaseRouter(WebDriver driver) {
        super(driver);
    }

    @Step("Opening page " + "{url}")
    public LoginPage openLoginPage(String url) {
        openUrl(url);
        AllureLogger.logToAllure("Page opened");
        return new LoginPage(driver);
    }
}
