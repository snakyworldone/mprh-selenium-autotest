
package com.monportailrh.loginTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.utility.AllureLogger;
import com.monportailrh.utility.model.Credential;
import com.monportailrh.utility.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.monportailrh.utility.GeneralPropertyManger.BASE_URL;

@Listeners({TestListener.class})
public class PositiveLoginTest extends BaseTest {
    @DataProvider(name = "defaultUsers")
    public Iterator<Object[]> createData() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credential.SUPERADMIN)});
        usersList.add(new Object[]{new User(Credential.ANGELINA_JOLIE)});
        usersList.add(new Object[]{new User(Credential.BRAD_PITT)});
        usersList.add(new Object[]{new User(Credential.DOLPH_LUNDGREN)});
        usersList.add(new Object[]{new User(Credential.JESSICA_ALBA)});
        return usersList.iterator();
    }

    @Test(dataProvider = "defaultUsers")
    public void logInTest(User testUser) {
        AllureLogger.logToAllure("Starting positive login test with admin credentials");

        baseRouter
                .openLoginPage(BASE_URL)
                .fillInUserName(testUser.getUsername())
                .fillInPassword(testUser.getPassword())
                .clickLoginButton()
                .validateLogin();
    }
}

