
package com.monportailrh.loginTest;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.utilities.AllureLogger;
import com.monportailrh.utilities.models.Credentials;
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
public class PositiveLoginTest extends BaseTest {
    @DataProvider(name = "defaultUsers")
    public Iterator<Object[]> createData() {
        List<Object[]> usersList = new ArrayList<>();
        usersList.add(new Object[]{new User(Credentials.SUPERADMIN)});
        usersList.add(new Object[]{new User(Credentials.ANGELINA_JOLIE)});
        usersList.add(new Object[]{new User(Credentials.BRAD_PITT)});
        usersList.add(new Object[]{new User(Credentials.DOLPH_LUNDGREN)});
        usersList.add(new Object[]{new User(Credentials.JESSICA_ALBA)});
        return usersList.iterator();
    }

    @Test(dataProvider = "defaultUsers", groups = {"smokes", "login"})
    @Description("Login with valid credentials")
    public void logInTest(User testUser) {
        AllureLogger.logToAllure("Starting positive login test");

        baseRouter
                .openLoginPage(BASE_URL)
                .fillInUserName(testUser.getUsername())
                .fillInPassword(testUser.getPassword())
                .clickLoginButton()
                .validateLogin();
    }
}

