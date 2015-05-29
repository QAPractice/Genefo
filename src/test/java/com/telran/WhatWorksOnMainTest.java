package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.WhatWorksOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 5/29/2015.
 */
public class WhatWorksOnMainTest {

    public WebDriver driver;
    public WebDriverWait wait;
    private boolean acceptNextAlert = true;

    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public WhatWorksOnMainPage whatWorksOnMainPage;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        whatWorksOnMainPage = PageFactory.initElements(driver, WhatWorksOnMainPage.class);

        try {
            loginPage.openLoginPage()
                            .waitUntilLoginPageIsLoaded()
                            .fillEmailField("telrantests@yahoo.com")
                            .fillPasswordField("12345.com")
                            .clickToLogin();

            mainPage.waitUntilMainPageIsLoaded()
                            .openWhatWorksButtonPanel();
            whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void SendTherapyPostTest() {

        try {
            whatWorksOnMainPage
                    .clickOnTherapyOption()
                    .clickOnItemList()
                    .chooseFirstItemFromItemList()
                    .fillTextField("My First Post")
                    .sendPost();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}
