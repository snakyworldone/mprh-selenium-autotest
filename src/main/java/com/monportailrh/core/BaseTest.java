package com.monportailrh.core;

import com.monportailrh.utility.GeneralPropertyManger;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
    private static final String CHROME_DRIVER = "chrome";

    protected WebDriver driver;
    protected Logger log;

    @Parameters({"propertiesFilePath"})
    @BeforeSuite(alwaysRun = true)
    public void setProperties(@Optional("config/dev.properties") String propertiesFilePath, ITestContext context) {
        GeneralPropertyManger.setProperties(propertiesFilePath);
        RestAssured.baseURI = GeneralPropertyManger.BASE_URL;
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional(CHROME_DRIVER) String browser, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing driver");
        driver.quit();
    }
}
