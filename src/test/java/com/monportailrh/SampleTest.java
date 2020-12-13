package com.monportailrh;

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
public class SampleTest extends BaseTest {

    @DataProvider(name = "defaultUsers")
    public Iterator<Object[]> createData() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credential.ANGELINA_JOLIE)});
        usersList.add(new Object[]{new User(Credential.BRAD_PITT)});
        usersList.add(new Object[]{new User(Credential.DOLPH_LUNDGREN)});
        usersList.add(new Object[]{new User(Credential.JESSICA_ALBA)});
        return usersList.iterator();
    }

    @Test(dataProvider = "defaultUsers")
    public void MyModuleWidgetIsVisible(User testUser) {
        Utility utility = new Utility();
        log.info("Starting positive login test with admin credentials");

        // Logging In and Verifying Login
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.validateLogin(testUser);

        // Assert that My modules widget visible
        MyModuleWidget myModuleWidget = new MyModuleWidget(driver, log);
        Assert.assertTrue(myModuleWidget.isMyWidgetVisible());
        log.info("[My Modules] widget is visible");

        List<String> expectedArrayOfModules = testUser.listAllModuleNames();
        List<String> actualArrayOfModules = myModuleWidget.listAllModuleNames();

        log.info("Actual array is: " + utility.listAllElements(actualArrayOfModules));
        log.info("Expected array is: " + utility.listAllElements(expectedArrayOfModules));

        Assert.assertEquals(actualArrayOfModules, expectedArrayOfModules, "Actual and Expected arrays are different");
    }
}
