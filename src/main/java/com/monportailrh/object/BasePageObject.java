package com.monportailrh.object;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePageObject {
    protected WebDriver driver;

    public BasePageObject() {
    }

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Open page with given URL
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Type given text into element with given locator
     */
    protected void type(String text, WebElement element) {
        waitForVisibilityOf(element, 5);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Click on element with given locator when its visible
     */
    protected void click(WebElement element) {
        waitForVisibilityOf(element, 5);
        element.click();
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    /**
     * Switch to another tab
     *
     * @param mainTab requires to get new tab from a list
     */
    public void switchToNewTab(String mainTab) {
        ArrayList<String> openedTabs;
        openedTabs = new ArrayList<>(driver.getWindowHandles());
        openedTabs.remove(mainTab);
        driver.switchTo().window(openedTabs.get(0));
    }

    /**
     * Switch to main tab
     */
    public void switchToMainTab(String mainTab) {
        driver.switchTo().window(mainTab);
    }

    /**
     * Closes current tab
     */
    public void closeCurrentTab() {
        driver.close();
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get title of current page
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get source of current page
     */
    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    public String getInnerText(WebElement element) {
        return element.getAttribute("textContent");
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(WebElement element, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOf(element),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            attempts++;
        }
    }
}
