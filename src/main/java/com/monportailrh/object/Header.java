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
    private WebElement headerSelfServiceButton;
    @FindBy(xpath = "//a[contains(text(), 'HR Request')]")
    private WebElement headerPlusButtonHrRequests;
    @FindBy(id = "PopoverMyTeam")
    private WebElement headerMyTeam;
    @FindBy(xpath = "//a[contains(@translation-id, 'web.containers.App.profileTooltip')]")
    private WebElement profileButton;

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Validating whether User logged in or not")
    public void validateLogin() {
        Assert.assertTrue(isLogoVisible());
        AllureLogger.logToAllure("User was successfully logged in");
    }

    private boolean isLogoVisible() {
        waitForVisibilityOf(headerLogo, 5);
        return headerLogo.isDisplayed();
    }
}
