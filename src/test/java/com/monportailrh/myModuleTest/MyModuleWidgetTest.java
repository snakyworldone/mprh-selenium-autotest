package com.monportailrh.myModuleTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.model.Credential;
import com.monportailrh.utility.model.MyModuleWidget;
import com.monportailrh.utility.model.User;
import com.monportailrh.utility.model.Utility;
import org.testng.Assert;
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

    // Test that checks links

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkVisibilityOfMyModulesWidgetForNonAdmin(User testUser) {
        log.info("Starting Test with Scenario: Check whether non Superadmin User has access to My Modules widget");

        // Logging In and Verifying Login
        loginPage = new LoginPage(driver, log);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        myModuleWidget = new MyModuleWidget(driver, log);
        Assert.assertTrue(myModuleWidget.validateMyModulesWidgetIsVisible());
        log.info("[My Modules] widget is visible");
    }

    @Test(dataProvider = "nonAdminTestUsers")
    public void checkModuleVisibilityForNonAdmins(User testUser) {
        Utility utility = new Utility();
        log.info("Starting Test with Scenario: Check Modules visibility based on permissions");

        // Logging In and Verifying Login
        loginPage = new LoginPage(driver, log);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        myModuleWidget = new MyModuleWidget(driver, log);
        Assert.assertTrue(myModuleWidget.validateMyModulesWidgetIsVisible());
        log.info("[My Modules] widget is visible");

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.getAllModuleNames();

        log.info("Actual array is: " + utility.listAllElements(actualArrayOfModules));
        log.info("Expected array is: " + utility.listAllElements(expectedArrayOfModules));

        Assert.assertEquals(actualArrayOfModules, expectedArrayOfModules, "Actual and Expected arrays are different");
    }
}
