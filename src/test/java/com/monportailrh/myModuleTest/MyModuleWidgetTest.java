package com.monportailrh.myModuleTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.utilities.AllureLogger;
import com.monportailrh.utilities.models.Credentials;
import com.monportailrh.utilities.models.MyModuleWidget;
import com.monportailrh.utilities.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.monportailrh.utilities.GeneralPropertyManger.BASE_URL;

@Listeners({TestListener.class})
@Severity(SeverityLevel.CRITICAL)
public class MyModuleWidgetTest extends BaseTest {
    private MyModuleWidget myModuleWidget;

    @DataProvider(name = "nonAdminTestUsers")
    public Iterator<Object[]> nonAdminTestUsers() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credentials.ANGELINA_JOLIE)});
        usersList.add(new Object[]{new User(Credentials.BRAD_PITT)});
        usersList.add(new Object[]{new User(Credentials.DOLPH_LUNDGREN)});
        usersList.add(new Object[]{new User(Credentials.JESSICA_ALBA)});
        return usersList.iterator();
    }

    @DataProvider(name = "adminTestUsers")
    public Iterator<Object[]> adminTestUsers() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credentials.SUPERADMIN)});
        return usersList.iterator();
    }

    @Test(dataProvider = "adminTestUsers")
    @Description("This test checks whether Admin is able to view My Modules widget or not")
    public void checkVisibilityOfMyModulesWidgetForAdmin(User testUser) {
        AllureLogger.logToAllure("Starting Test to check MyModules widget visibility");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsNotVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    @Description("This test checks whether non-Admin is able to view My Modules widget or not")
    public void checkVisibilityOfMyModulesWidgetForNonAdmin(User testUser) {
        AllureLogger.logToAllure("Starting Test to check MyModules widget visibility");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    @Description("This test checks displayed modules and compares to array of modules returned by api")
    public void checkDisplayedModules(User testUser) {
        AllureLogger.logToAllure("Starting Test to check Displayed modules");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();


        List<String> expectedArrayOfModules = testUser.getListWithAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getActualArrayOfModules();

        myModuleWidget
                .validateActualAndExpectedModuleArraysAreEqual(expectedArrayOfModules, actualArrayOfModules);
    }

    @Test(dataProvider = "nonAdminTestUsers")
    @Description("This test checks redirection links for all displayed modules and compares to redirection links returned by api ")
    public void checkModuleRedirection(User testUser) {
        AllureLogger.logToAllure("Starting Test to check module redirection");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();

        List<String> expectedArrayOfModules = testUser.getListWithAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getActualArrayOfModules();

        myModuleWidget
                .validateActualAndExpectedModuleArraysAreEqual(expectedArrayOfModules, actualArrayOfModules)
                .validateModuleRedirection(testUser);
    }
}
