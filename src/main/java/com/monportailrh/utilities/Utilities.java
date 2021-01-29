package com.monportailrh.utilities;

import com.monportailrh.core.BaseTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utilities extends BaseTest {

    public String listAllElements(List<String> listArray) {
        return Arrays.toString(listArray.toArray());
    }

    /**
     * Take screenshot and save in project directory
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public void takeScreenshot(WebDriver webDriver) {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
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

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] attachScreenshotToAllureReport(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

}
