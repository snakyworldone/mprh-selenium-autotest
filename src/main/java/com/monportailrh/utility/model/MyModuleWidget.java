package com.monportailrh.utility.model;

import com.monportailrh.object.MainPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MyModuleWidget extends MainPage {
    @FindBy(xpath = "//h4[contains(text(), 'My Modules')]")
    private WebElement title;
    @FindAll({@FindBy(css = "div.dark-gray.p-2.col-sm-12.col-md-4.col-lg-4")})
    private List<WebElement> listOfModules;
    private WebElement extendArrow;

    public MyModuleWidget(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public boolean isMyWidgetVisible() {
        waitForVisibilityOf(title, 5);
        return title.isDisplayed();
    }

    public List<String> listAllModuleNames() {
        List<String> myModules = new ArrayList<>();
        for (WebElement element : listOfModules) {
            myModules.add(element.getAttribute("innerText"));
        }
        return myModules;
    }
}
