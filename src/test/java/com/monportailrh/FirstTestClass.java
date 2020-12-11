package com.monportailrh;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.Header;
import com.monportailrh.object.LoginPage;
import com.monportailrh.object.MainPage;
import com.monportailrh.utility.model.Credential;
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
public class FirstTestClass extends BaseTest {


    @DataProvider(name = "defaultUsers")
    public Iterator<Object[]> createData() {
        List<Object[]> usersList = new ArrayList<>();
        //usersList.add(new Object[]{new User(Credential.SUPERADMIN)});
        usersList.add(new Object[]{new User(Credential.ANGELINA_JOLIE)});
        return usersList.iterator();
    }

    @Test(dataProvider = "defaultUsers")
    public void MyModuleWidgetIsVisible(User user) {
        log.info("Starting positive login test with admin credentials");
        // Logging In
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.fastLogIn(user);

        // Verifying that logged in
        Header header = new Header(driver, log);
        header.isLogoVisible();

        // Assert that My modules widget visible
        MainPage mainPage = new MainPage(driver, log);
        Assert.assertTrue(mainPage.isMyWidgetVisible());

        List<String> expectedArrayOfModules = user.listAllModuleNames();
        List<String> actualArrayOfModules = mainPage.listAllModuleNames();

        Utility utility = new Utility();
        System.out.println("Actual array is: " + utility.listAllElements(actualArrayOfModules));
        System.out.println("Expected array is: " + utility.listAllElements(expectedArrayOfModules));

    }
}
