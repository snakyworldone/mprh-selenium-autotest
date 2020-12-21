package com.monportailrh.myModuleTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.model.Credential;
import com.monportailrh.utility.model.MyModuleWidget;
import com.monportailrh.utility.model.User;
import com.monportailrh.utility.Utility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners({TestListener.class})
public class MyModuleWidgetTest extends BaseTest {
    private LoginPage loginPage;
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

        // Logging In and Verifying Login
        loginPage = new LoginPage(driver);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        myModuleWidget = new MyModuleWidget(driver);
        myModuleWidget.validateMyModulesWidgetIsNotVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkVisibilityOfMyModulesWidgetForNonAdmin(User testUser) {
        AllureLogger.logToAllure("Starting Test to check MyModules widget visibility");

        // Logging In and Verifying Login
        loginPage = new LoginPage(driver);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        myModuleWidget = new MyModuleWidget(driver);
        myModuleWidget.validateMyModulesWidgetIsVisible();
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkDisplayedModules(User testUser) {
        Utility utility = new Utility();
        AllureLogger.logToAllure("Starting Test to check Displayed modules");

        // Logging In and Verifying Login
        loginPage = new LoginPage(driver);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        myModuleWidget = new MyModuleWidget(driver);
        myModuleWidget.validateMyModulesWidgetIsVisible();

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getAllModuleNames();
        myModuleWidget.validateArraysAreEqual(expectedArrayOfModules, actualArrayOfModules);
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkModuleRedirection(User testUser) {
        Utility utility = new Utility();
        AllureLogger.logToAllure("Starting Test to check module redirection");

        // Logging In and Verifying Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        MyModuleWidget myModuleWidget = new MyModuleWidget(driver);
        myModuleWidget.validateMyModulesWidgetIsVisible();

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getAllModuleNames();
        myModuleWidget.validateArraysAreEqual(expectedArrayOfModules, actualArrayOfModules);

        myModuleWidget.validateAllAvailableModules(testUser);
    }
}
