package com.monportailrh.object;

import com.monportailrh.utilities.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Header extends BasePageObject {
    @FindBy(xpath = "//img[@class=\"peoplespere-logo\"]")
    private WebElement headerLogo;
    @FindBy(id = "search-input")
    private WebElement headerSearchBar;
    @FindBy(xpath = "//button[contains(@title, 'Self Service')]")
    private WebElement headerPlusButton;
    // try to use //div[contains(@class, 'selfservice-container')]//li/a[contains(text(), 'HR Request')]
    @FindBy(xpath = "//a[contains(text(), 'HR Request')]")
    private WebElement headerPlusButtonHrRequests;
    @FindBy(id = "PopoverMyTeam")
    private WebElement headerMyTeam;

    public Header(WebDriver driver) {
        super(driver);
    }

    private boolean isLogoVisible() {
        waitForVisibilityOf(headerLogo, 5);
        return headerLogo.isDisplayed();
    }

    @Step("Validating whether User logged in or not")
    public void validateLogin() {
        Assert.assertTrue(isLogoVisible());
        AllureLogger.logToAllure("User was successfully logged in");
    }
}
