package com.monportailrh.utility.model;

import com.monportailrh.object.MainPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyModuleWidget extends MainPage {
    @FindBy(xpath = "//h4[contains(text(), 'My Modules')]")
    public WebElement title;
    @FindAll({@FindBy(css = "div.dark-gray.p-2.col-sm-12.col-md-4.col-lg-4")})
    public List<WebElement> listOfModules;
    public WebElement extendArrow;

    public MyModuleWidget(WebDriver driver, Logger log) {
        super(driver, log);
    }
}
