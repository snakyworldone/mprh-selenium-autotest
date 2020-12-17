package com.monportailrh.utility.model;

import com.monportailrh.object.BasePageObject;
import com.monportailrh.utility.RestAssuredUtilityManager;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyModuleWidget extends BasePageObject {
    private String mainWindowHandle;
    private static final int MODULES_BEFORE_ICON = 6;
    @FindBy(xpath = "//h4[contains(text(), 'My Modules')]")
    private WebElement title;
    @FindAll({@FindBy(css = "div.px-2")})
    private List<WebElement> listOfModules;
    @FindBy(xpath = "//div[contains(@class, 'mb137')]//div[contains(@class, 'icon-down-arrow')]")
    private WebElement extendArrow;

    public MyModuleWidget(WebDriver driver, Logger log) {
        super(driver, log);

    }

    public void clickOnExtendArrow() {
        click(extendArrow);
        for (WebElement element : listOfModules) {
            waitForClickabilityOf(element, 5);
        }
    }

    public void validateMyModulesWidgetIsVisible() {
        waitForVisibilityOf(title, 5);
        Assert.assertTrue(title.isDisplayed());
        log.info("[My Modules] widget is visible");
    }

    public List<String> getAllModuleNames() {
        List<String> myModules = new ArrayList<>();
        for (WebElement element : getListOfModules()) {
            myModules.add(element.getAttribute("innerText"));
        }
        return myModules;
    }

    public List<String> getAllModuleLinks(User testUser) {
        mainWindowHandle = driver.getWindowHandle();

        if (listOfModules.size() > MODULES_BEFORE_ICON) {
            clickOnExtendArrow();
        }

        for (WebElement element : getListOfModules()) {
            log.info("Clicking on [" + element.getAttribute("innerText") + "] module");
            element.click();
            switchToNewTab(mainWindowHandle);


            log.info("Current URL is: " + getCurrentUrl());
            log.info("Expected URL is: " + testUser.getListOfModules().);
            closeCurrentTab();
            switchToMainTab(mainWindowHandle);
        }
    }

}