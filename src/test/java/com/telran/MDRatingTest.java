package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MDRatingOnMainPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName", "text"})
    public void sendMDRatingPostTest(String facilityname, String physitianFirstName, String physitianLastName, String text) {
        facilityname = "chicagoMed";
        physitianFirstName = "Phil";
        physitianLastName = "Richards";
        text = "post";
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facilityname)
                    .fillPhysicianFields(physitianFirstName, physitianLastName)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField(text)
                    .sendPost()
                    .waitUntilNewPostisLoaded();

            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow());
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(facilityname));
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianFirstNameCorrect(physitianFirstName));
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianLastNameCorrect(physitianLastName));
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect(text));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*@AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }*/

}

