package com.monportailrh;

import com.monportailrh.core.BaseTest;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.GeneralPropertyManger;
import org.testng.annotations.Test;

public class SecondTestClass extends BaseTest {
    @Test
    public void sssss() {
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openLoginPage(GeneralPropertyManger.BASE_URL);
    }
}
