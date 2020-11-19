package com.monportailrh.object;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageObject {
    @FindBy(id = "username")
    private WebElement userNameInput;

    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
}
