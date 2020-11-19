package com.monportailrh.object;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePageObject {
    @FindBy(xpath = "//img[@class=\"peoplespere-logo\"]")
    private WebElement headerLogo;
    @FindBy(id = "search-input")
    private WebElement headerSearchBar;
    @FindBy(xpath = "//button[contains(@title, 'Self Service')]")
    private WebElement headerPlusButton;
    // try to use //a[contains(text(), 'HR Request')]
    @FindBy(xpath = "//div[contains(@class, 'selfservice-container')]//li/a[contains(text(), 'HR Request')]")
    private WebElement headerPlusButtonHrRequests;
    @FindBy(id = "PopoverMyTeam")
    private WebElement headerMyTeam;

    public Header(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public boolean isLogoVisible() {
        waitForVisibilityOf(headerLogo, 5);
        return headerLogo.isDisplayed();
    }
}
