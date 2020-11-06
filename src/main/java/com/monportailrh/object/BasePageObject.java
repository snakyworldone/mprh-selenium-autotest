package com.monportailrh.object;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    protected void openUrl(String url) {
        driver.get(url);
    }
}
