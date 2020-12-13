package com.monportailrh.object;

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

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openLoginPage(String url) {
        log.info("Opening page: " + url);
        openUrl(url);
        log.info("Page opened!");
    }

    public void fillInUserName(String username) {
        log.info("Providing [" + username + "] into username input field");
        type(username, userNameInput);
    }

    public void fillInPassword(String password) {
        log.info("Providing [" + password + "] into password input field");
        type(password, passwordInput);
    }

    public void clickLoginButton() {
        log.info("Clicking on Login button");
        click(loginButton);
    }

    public void validateLogin(User testUser) {
        Header header = new Header(driver, log);
        openLoginPage(BASE_URL);
        fillInUserName(testUser.getUsername());
        fillInPassword(testUser.getPassword());
        clickLoginButton();
        log.info("Validating whether User logged in or not");
        Assert.assertTrue(header.isLogoVisible());
        log.info("User was successfully logged in");
    }

}