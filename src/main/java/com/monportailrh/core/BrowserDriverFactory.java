package com.monportailrh.core;

import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.GeneralPropertyManger;
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
    private static final String SYSTEM_PROPERTY_CHROME_PATH_LINUX = "src/main/resources/drivers/linux/chromedriver";
    private static final String SYSTEM_PROPERTY_FIREFOX_PATH = "\"src/main/resources/drivers/mac/geckodriver";
    private static final String SYSTEM_PROPERTY_OS_MAC = "Mac OS X";
    private static final String SYSTEM_PROPERTY_OS_LINUX = "Linux";
    private static final String SYSTEM_PROPERTY_BROWSER_CHROME = "chrome";
    private static final String SYSTEM_PROPERTY_BROWSER_CHROME_HEADLESS = "chrome --headless";
    private static final String SYSTEM_PROPERTY_BROWSER_FF = "firefox";
    private static final String REMOTE_DRIVER_URL = GeneralPropertyManger.REMOTE_DRIVER_URL;

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String currentOs = System.getProperty("os.name");
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() throws MalformedURLException {
        AllureLogger.logToAllure("Creating driver: " + browser);

        if (SYSTEM_PROPERTY_OS_MAC.equals(currentOs)) {
            getConfiguredBrowserForMac(browser);
        } else if (SYSTEM_PROPERTY_OS_LINUX.equals(currentOs)) {
            getConfiguredBrowserForLinux(browser);
        } else {
            AllureLogger.logToAllure("Currently, this OS [" + currentOs + "] is not yet supported");
        }
        return driver.get();
    }

    private void getConfiguredBrowserForMac(String browser) throws MalformedURLException {
        switch (browser) {
            case SYSTEM_PROPERTY_BROWSER_CHROME:
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                driver.set(new ChromeDriver());
                break;
            case SYSTEM_PROPERTY_BROWSER_CHROME_HEADLESS:
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                driver.set(new RemoteWebDriver(new URL(REMOTE_DRIVER_URL), new ChromeOptions().setHeadless(true)));
                break;
            case SYSTEM_PROPERTY_BROWSER_FF:
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

    private void getConfiguredBrowserForLinux(String browser) throws MalformedURLException {
        switch (browser) {
            case SYSTEM_PROPERTY_BROWSER_CHROME_HEADLESS:
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH_LINUX);
                driver.set(new RemoteWebDriver(new URL(REMOTE_DRIVER_URL), new ChromeOptions().setHeadless(true)));
                break;
            default:
                System.out.println("Currently, this browser [" + browser + "] us not yet supported. Starting Chrome");
                System.setProperty(SYSTEM_PROPERTY_CHROME, SYSTEM_PROPERTY_CHROME_PATH);
                driver.set(new ChromeDriver());
                break;
        }
    }
}
