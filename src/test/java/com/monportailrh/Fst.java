package com.monportailrh;

import com.monportailrh.core.BaseTest;
import com.monportailrh.core.TestListener;
import com.monportailrh.object.LoginPage;
import com.monportailrh.utility.GeneralPropertyManger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class Fst extends BaseTest {

    public void sssss() {
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage(GeneralPropertyManger.SSO_ENDPOINT);
    }
}
