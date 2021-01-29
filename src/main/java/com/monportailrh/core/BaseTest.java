package com.monportailrh.core;

import com.monportailrh.utilities.AllureLogger;
import com.monportailrh.utilities.GeneralPropertyManger;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

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
        ctx.setAttribute("webDriver", driver);

        this.testSuiteName = ctx.getSuite().getName();
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testMethodName = method.getName();
    }

    @AfterMethod
    public void tearDown() {
        AllureLogger.logToAllure("Closing driver");
        driver.quit();
    }
}
