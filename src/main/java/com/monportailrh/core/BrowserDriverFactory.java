package com.monportailrh.core;

import com.monportailrh.utility.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory {
    private static final String SYSTEM_PROPERTY_CHROME = "webdriver.chrome.driver";
    private static final String SYSTEM_PROPERTY_FF = "webdriver.gecko.driver";
    private static final String SYSTEM_PROPERTY_CHROME_PATH = "src/main/resources/drivers/mac/chromedriver";
    private static final String SYSTEM_PROPERTY_FIREFOX_PATH = "\"src/main/resources/drivers/mac/geckodriver";

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String currentOs = System.getProperty("os.name");
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() throws MalformedURLException {
        AllureLogger.logToAllure("Creating driver: " + browser);

        if ("Mac OS X".equals(currentOs)) {
            getConfiguredBrowserForMac(browser);
        } else {
            AllureLogger.logToAllure("Currently, this OS is not yet supported");
        }
        return driver.get();
    }

    private void getConfiguredBrowserForMac(String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                driver.set(new ChromeDriver());
                break;
            case "chrome --headless":
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                //driver.set(new RemoteWebDriver(new ChromeOptions().addArguments("--headless")));
                //setHeadless(true)
                driver.set(new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), new ChromeOptions().addArguments("--headless")));
                break;
            case "firefox":
                System.setProperty(SYSTEM_PROPERTY_FF, SYSTEM_PROPERTY_FIREFOX_PATH);
                driver.set(new FirefoxDriver());
                break;
            default:
                System.out.println("Currently, this browser [" + browser + "] us not yet supported. Starting Chrome");
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                driver.set(new ChromeDriver());
                break;
        }
    }
}
