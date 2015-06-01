package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.UpperSentPostTabOnMainPage;
import com.telran.pages.WhatWorksOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by alex on 5/29/2015.
 */
public class WhatWorksOnMainTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public UpperSentPostTabOnMainPage upperSentPostTabOnMainPage;
    public WhatWorksOnMainPage whatWorksOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        whatWorksOnMainPage = PageFactory.initElements(driver, WhatWorksOnMainPage.class);
        upperSentPostTabOnMainPage = PageFactory.initElements(driver,UpperSentPostTabOnMainPage.class);

        try {
            loginPage.login("telrantests@yahoo.com", "12345.com");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                            .openWhatWorksButtonPanel();
            whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void SendTherapyPostTest() {
        String text = "My Fifth Post" ;
        try {
            whatWorksOnMainPage
                    .clickOnTherapyOption()
                    .clickOnItemList()
                    .chooseFirstItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(3000);

            assertTrue(upperSentPostTabOnMainPage.verifyTextFromSentPost(text));
            assertTrue(upperSentPostTabOnMainPage.verifyCategoryTherapyExists());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}
