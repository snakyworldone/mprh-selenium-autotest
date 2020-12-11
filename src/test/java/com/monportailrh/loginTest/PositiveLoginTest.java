
package com.monportailrh.loginTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.Header;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.GeneralPropertyManger;
import com.monportailrh.utility.model.Credential;
import com.monportailrh.utility.model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners({TestListener.class})
public class PositiveLoginTest extends BaseTest {
    @DataProvider(name = "defaultUsers")
    public Iterator<Object[]> createData() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credential.SUPERADMIN)});
        usersList.add(new Object[]{new User(Credential.ANGELINA_JOLIE)});
        return usersList.iterator();
    }

    @Test(dataProvider = "defaultUsers")
    public void logInAsAdminTest(User user) {
        log.info("Starting positive login test with admin credentials");
        LoginPage loginPage = new LoginPage(driver, log);

        // Opening SSO LogIn page
        loginPage.openLoginPage(GeneralPropertyManger.BASE_URL);

        // Providing Username and Password
        loginPage.fillInUserName(user.getUsername());
        loginPage.fillInPassword(user.getPassword());

        // Clicking on LogIn button
        loginPage.clickLoginButton();
        Header header = new Header(driver, log);

        // Asserting that Header logo is visible
        Assert.assertTrue(header.isLogoVisible());
    }
}

