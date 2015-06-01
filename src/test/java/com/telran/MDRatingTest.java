package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MDRatingOnMainPage;
import com.telran.pages.MainPage;
import com.telran.pages.WhatWorksOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/30/2015.
 */
public class MDRatingTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MDRatingOnMainPage mdRatingOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        mdRatingOnMainPage = PageFactory.initElements(driver, MDRatingOnMainPage.class);

        try {
            loginPage.login("ri-lopatina@yandex.ru", "123456");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMDRatingButtonPanel();
            mdRatingOnMainPage.isOnMDRatingPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SendMDRatingPostTest() {

        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField("MMM")
                    .fillPhysicianFields("PPP", "SSS")
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField("My First Post")
                    .sendPost();
            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*@AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }*/

}

