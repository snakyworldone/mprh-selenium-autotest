package com.monportailrh.object;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePageObject {
    @FindBy(how = How.CLASS_NAME, using = "")
    String image;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage(String url) {
        log.info("Opening page: " + url);
        openUrl(url);
        log.info("Page opened!");
    }
}