package com.monportailrh.utility.model;

import com.monportailrh.object.BasePageObject;
import com.monportailrh.utility.AllureLogger;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

    public MyModuleWidget(WebDriver driver) {
        super(driver);

    }

    private void clickOnExtendArrow() {
        click(extendArrow);
    }

    private void validateActualAndExpectedModuleLinks(User testUser) {
        String moduleName;
        String actualUrl;
        String expectedUrl;

        for (WebElement element : getListOfModules()) {
            moduleName = getInnerText(element);
            expectedUrl = testUser.getMapOfModules().get(moduleName).getWebUrl();

            AllureLogger.logToAllure("Clicking on [" + moduleName + "] module");
            element.click();
            switchToNewTab(mainWindowHandle);
            actualUrl = getCurrentUrl();

            Assert.assertEquals(actualUrl, expectedUrl);
            AllureLogger.logToAllure("Actual URL equals to Expected");

            closeCurrentTab();
            switchToMainTab(mainWindowHandle);
        }
    }

    private boolean validateMyModulesWidget() {
        try {
            waitForVisibilityOf(getTitle(), 5);
        } catch (TimeoutException ignored) {
        }
        try {
            return getTitle().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public MyModuleWidget validateMyModulesWidgetIsVisible() {
        Assert.assertTrue(validateMyModulesWidget());
        AllureLogger.logToAllure("[My Modules] widget is visible");
        return this;
    }

    public MyModuleWidget validateMyModulesWidgetIsNotVisible() {
        Assert.assertFalse(validateMyModulesWidget());
        AllureLogger.logToAllure("[My Modules] widget is not visible");
        return this;
    }

    public MyModuleWidget validateActualAndExpectedModuleArraysAreEqual(List<String> expectedArrayOfModules, List<String> actualArrayOfModules) {
        Assert.assertEquals(actualArrayOfModules, expectedArrayOfModules);
        AllureLogger.logToAllure("Arrays are equal");
        return this;
    }

    public List<String> getActualArrayOfModules() {
        List<String> myModules = new ArrayList<>();
        for (WebElement element : getListOfModules()) {
            myModules.add(getInnerText(element));
        }
        return myModules;
    }

    public List<String> getExpectedArrayOfModules(User testUser) {
        return testUser.getListWithAllModuleNames();
    }

    public MyModuleWidget validateModuleRedirection(User testUser) {
        mainWindowHandle = driver.getWindowHandle();
        if (listOfModules.size() > MODULES_BEFORE_ICON) {
            clickOnExtendArrow();
        }
        validateActualAndExpectedModuleLinks(testUser);
        AllureLogger.logToAllure("All modules were successfully verified");
        return this;
    }

}