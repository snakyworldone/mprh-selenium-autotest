package com.monportailrh.object;

import com.monportailrh.model.Users;
import com.monportailrh.utility.GeneralPropertyManger;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {
    //@FindBy(xpath = "//*[@id=\"username\"]")
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

    public Header clickLoginButton() {
        log.info("Clicking on Login button");
        click(loginButton);
        return new Header(driver, log);
    }

    public void loginAsAdmin() {
        openLoginPage(GeneralPropertyManger.BASE_URL);
        fillInUserName(Users.SUPERADMIN.getUsername());
        fillInPassword(Users.SUPERADMIN.getPassword());
        clickLoginButton();
    }

}