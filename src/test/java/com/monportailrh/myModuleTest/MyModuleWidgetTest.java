package com.monportailrh.myModuleTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.model.Credential;
import com.monportailrh.utility.model.MyModuleWidget;
import com.monportailrh.utility.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.monportailrh.utility.GeneralPropertyManger.BASE_URL;

@Listeners({TestListener.class})
public class MyModuleWidgetTest extends BaseTest {
    private MyModuleWidget myModuleWidget;

    @DataProvider(name = "nonAdminTestUsers")
    public Iterator<Object[]> nonAdminTestUsers() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credential.ANGELINA_JOLIE)});
        usersList.add(new Object[]{new User(Credential.BRAD_PITT)});
        usersList.add(new Object[]{new User(Credential.DOLPH_LUNDGREN)});
        usersList.add(new Object[]{new User(Credential.JESSICA_ALBA)});
        return usersList.iterator();
    }

    @DataProvider(name = "adminTestUsers")
    public Iterator<Object[]> adminTestUsers() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credential.SUPERADMIN)});
        return usersList.iterator();
    }

    @Test(dataProvider = "adminTestUsers")
    public void checkVisibilityOfMyModulesWidgetForAdmin(User testUser) {
        AllureLogger.logToAllure("Starting Test to check MyModules widget visibility");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsNotVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkVisibilityOfMyModulesWidgetForNonAdmin(User testUser) {
        AllureLogger.logToAllure("Starting Test to check MyModules widget visibility");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkDisplayedModules(User testUser) {
        AllureLogger.logToAllure("Starting Test to check Displayed modules");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getAllModuleNames();

        myModuleWidget
                .validateArraysAreEqual(expectedArrayOfModules, actualArrayOfModules);
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkModuleRedirection(User testUser) {
        AllureLogger.logToAllure("Starting Test to check module redirection");

        myModuleWidget = baseRouter
                .openLoginPage(BASE_URL)
                .validateLogin(testUser, new MyModuleWidget(driver))
                .validateMyModulesWidgetIsVisible();

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getAllModuleNames();

        myModuleWidget
                .validateArraysAreEqual(expectedArrayOfModules, actualArrayOfModules)
                .validateAllAvailableModules(testUser);
    }
}
