package com.monportailrh.object;

import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.model.User;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePageObject {

    @FindBy(id = "username")
    private WebElement userNameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "kc-form-login")
    private WebElement loginForm;
    @FindBy(id = "kc-login")
    private WebElement loginButton;
    @FindBy(id = "unauthorized")
    private WebElement unauthorizedText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage(String url) {
        AllureLogger.logToAllure("Opening page: " + url);
        openUrl(url);
        AllureLogger.logToAllure("Page opened!");
    }

    public void fillInUserName(String username) {
        AllureLogger.logToAllure("Providing [" + username + "] into username input field");
        type(username, userNameInput);
    }

    public void fillInPassword(String password) {
        AllureLogger.logToAllure("Providing [" + password + "] into password input field");
        type(password, passwordInput);
    }

    public void clickLoginButton() {
        AllureLogger.logToAllure("Clicking on Login button");
        click(loginButton);
    }

    public void validateLogin(User testUser) {
        Header header = new Header(driver);
        openLoginPage(BASE_URL);
        fillInUserName(testUser.getUsername());
        fillInPassword(testUser.getPassword());
        clickLoginButton();
        header.validateLogin();
    }

}