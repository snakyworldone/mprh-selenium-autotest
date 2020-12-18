package com.monportailrh.utility.model;

import com.monportailrh.object.BasePageObject;
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
    //not unique xpath
    @FindBy(xpath = "//div[contains(@class, 'mb137')]//div[contains(@class, 'icon-down-arrow')]")
    private WebElement extendArrow;

    public MyModuleWidget(WebDriver driver, Logger log) {
        super(driver, log);

    }

    private void clickOnExtendArrow() {
        click(extendArrow);
    }

    private void validateModules(User testUser) {
        String moduleName;
        String actualUrl;
        String expectedUrl;

        for (WebElement element : getListOfModules()) {
            moduleName = element.getAttribute("innerText");
            expectedUrl = testUser.getMapOfModules().get(moduleName).getWebUrl();

            log.info("Clicking on [" + moduleName + "] module");
            element.click();
            switchToNewTab(mainWindowHandle);
            actualUrl = getCurrentUrl();

            Assert.assertEquals(actualUrl, expectedUrl);
            log.info("Actual URL equals to Expected");

            closeCurrentTab();
            switchToMainTab(mainWindowHandle);
        }
    }

    public void validateMyModulesWidgetIsVisible() {
        waitForVisibilityOf(getTitle(), 5);
        Assert.assertTrue(getTitle().isDisplayed());
        log.info("[My Modules] widget is visible");
    }

    public void validateArraysAreEqual(List<String> expectedArrayOfModules, List<String> actualArrayOfModules) {
        Assert.assertEquals(actualArrayOfModules, expectedArrayOfModules);
        log.info("Arrays are equal");
    }

    public List<String> getAllModuleNames() {
        List<String> myModules = new ArrayList<>();
        for (WebElement element : getListOfModules()) {
            myModules.add(element.getAttribute("innerText"));
        }
        return myModules;
    }

    public void validateAllAvailableModules(User testUser) {
        mainWindowHandle = driver.getWindowHandle();
        if (listOfModules.size() > MODULES_BEFORE_ICON) {
            clickOnExtendArrow();
        }
        validateModules(testUser);
        log.info("All modules were successfully verified");
    }

}