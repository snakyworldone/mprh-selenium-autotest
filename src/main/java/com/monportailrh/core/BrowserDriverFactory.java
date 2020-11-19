package com.monportailrh.core;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
    private static final String SYSTEM_PROPERTY_CHROME = "webdriver.chrome.driver";
    private static final String SYSTEM_PROPERTY_FF = "webdriver.gecko.driver";
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;
    private String currentOs = System.getProperty("os.name");

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver() {
        log.info("Creating driver: " + browser);
        if ("Mac OS X".equals(currentOs)) {
            getConfiguredBrowserForMac(browser);
        } else {
            System.out.println("Currently, this OS is not yet supported");
        }
        return driver.get();
    }

    // Move these paths to config
    private void getConfiguredBrowserForMac(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty(SYSTEM_PROPERTY_CHROME, "src/main/resources/drivers/mac/chromedriver");
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty(SYSTEM_PROPERTY_FF, "src/main/resources/drivers/mac/geckodriver");
                driver.set(new FirefoxDriver());
                break;
            default:
                System.out.println("Currently, this browser [" + browser + "] us not yet supported. Starting Chrome");
                System.setProperty(SYSTEM_PROPERTY_CHROME, "src/main/resources/drivers/mac/chromedriver");
                driver.set(new ChromeDriver());
                break;
        }
    }
}
