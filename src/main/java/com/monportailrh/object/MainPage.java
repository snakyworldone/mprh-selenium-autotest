package com.monportailrh.object;

import com.monportailrh.utility.model.MyModuleWidget;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePageObject {
    private MyModuleWidget myModulesWidget;

    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public boolean isMyWidgetVisible() {
        myModulesWidget = new MyModuleWidget(driver, log);
        waitForVisibilityOf(myModulesWidget.title, 5);
        return myModulesWidget.title.isDisplayed();
    }

    public List<String> listAllModuleNames() {
        List<String> myModules = new ArrayList<>();
        myModulesWidget = new MyModuleWidget(driver, log);
        System.out.println("List size " + myModulesWidget.listOfModules.size());
        for (WebElement element : myModulesWidget.listOfModules) {
            myModules.add(element.getAttribute("innerText"));
            //System.out.println(element.getAttribute("innerText"));
        }
        return myModules;
    }
}
