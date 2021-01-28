package com.monportailrh.core;

import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.GeneralPropertyManger;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    private static final String CHROME_DRIVER_NAME = "chrome --headless";
    private static final String DEFAULT_CONFIG_PATH = "config/qa.properties";
    private static final String BROWSER_PARAMETER = "browser";
    private static final String PROPERTY_PATH_PARAMETER = "propertiesFilePath";

    protected WebDriver driver;
    protected BaseRouter baseRouter;
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    @Parameters({PROPERTY_PATH_PARAMETER})
    @BeforeSuite(alwaysRun = true)
    public void setProperties(@Optional(DEFAULT_CONFIG_PATH) String propertiesFilePath, ITestContext context) {
        GeneralPropertyManger.setProperties(propertiesFilePath);
        RestAssured.baseURI = GeneralPropertyManger.BASE_URL_API;
    }

    @Parameters({BROWSER_PARAMETER})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional(CHROME_DRIVER_NAME) String browser, ITestContext ctx) throws MalformedURLException {
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        driver = factory.createDriver();
        driver.manage().window().setSize(new Dimension(1920, 1200));
        baseRouter = new BaseRouter(driver);

        this.testSuiteName = ctx.getSuite().getName();
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testMethodName = method.getName();
    }

    @AfterMethod
    public void tearDown() {
        AllureLogger.logToAllure("takeScreenshot");
        takeScreenshot();
        AllureLogger.logToAllure("Closing driver");
        driver.quit();
    }

    /**
     * Take screenshot
     */
    public void takeScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "screenshots"
                + File.separator + getTodaysDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime() + "s" + ".png";
        // + " " + fileName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Todays date in yyyyMMdd format
     */
    private static String getTodaysDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    /**
     * Current time in HHmmssSSS
     */
    private String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }
}
