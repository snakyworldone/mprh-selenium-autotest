package com.monportailrh.object;

import com.monportailrh.utilities.AllureLogger;
import com.monportailrh.utilities.models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @Step("Fill in username")
    public LoginPage fillInUserName(String username) {
        AllureLogger.logToAllure("Providing [" + username + "] into username input field");
        type(username, userNameInput);
        return this;
    }

    @Step("Fill in password")
    public LoginPage fillInPassword(String password) {
        AllureLogger.logToAllure("Providing [" + password + "] into password input field");
        type(password, passwordInput);
        return this;
    }

    @Step("Click on login button")
    public Header clickLoginButton() {
        AllureLogger.logToAllure("Clicking on Login button");
        click(loginButton);
        return new Header(driver);
    }

    @Step("Fill in Login form and proceed")
    public <T> T completeLoginFormAndProceed(User testUser, T t) {
        fillInUserName(testUser.getUsername())
                .fillInPassword(testUser.getPassword())
                .clickLoginButton()
                .validateLogin();
        return t;
    }

}