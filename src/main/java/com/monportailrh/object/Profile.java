package com.monportailrh.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Profile extends BasePageObject {

    @FindBy(xpath = "//div[contains(@class, 'profile-title')]/h2")
    private WebElement profileName;
    @FindBy(xpath = "//div[contains(@class, 'input-group')]/input")
    private WebElement searchBar;
    @FindBy(xpath = "//h4[contains(@class, 'categoryHeading')]")
    private WebElement categoryTitle;
    @FindAll({@FindBy(xpath = "//div[contains(@class, 'm-bottom px-2 row')]/div")})
    private List<WebElement> listOfCategories;
    @FindAll({@FindBy(xpath = "//ul[contains(@class, 'list-group')]/li")})
    private List<WebElement> listOfFields;

    public Profile(WebDriver driver) {
        super(driver);
    }
}
