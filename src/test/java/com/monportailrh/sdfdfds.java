package com.monportailrh;

import com.monportailrh.core.BaseTest;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.GeneralPropertyManger;
import org.testng.annotations.Test;

public class sdfdfds extends BaseTest {
    @Test
    public void sssss() {
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage(GeneralPropertyManger.SSO_ENDPOINT);
    }
}
