package com.monportailrh;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.Header;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.GeneralPropertyManger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class FirstTestClass extends BaseTest {

    @Test//(dataProvider = "pageObjects")
    public void FirstTest() {
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openLoginPage(GeneralPropertyManger.BASE_URL);
        loginPage.fillInUserName(GeneralPropertyManger.SUPERADMIN_USERNAME);
        loginPage.fillInPassword(GeneralPropertyManger.SUPERADMIN_PASSWORD);

    }
}
