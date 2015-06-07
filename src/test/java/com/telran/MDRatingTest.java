package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MDRatingOnMainPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/30/2015.
 */
public class MDRatingTest {
    private static String EMAIL = "ri-lopatina@yandex.ru";
    private static String PASSWORD = "123456";
    private static String FACILITY_NAME = "chicagoMed";
    private static String PHYSICIAN_FNAME = "Phil";
    private static String PHYSICIAN_LNAME = "Richards";
    private static String TEXT = "post";
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
            loginPage.login(EMAIL, PASSWORD);
            try {
                Thread.sleep(2000);
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

    @Test (groups = {"smoke", "positive"})
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName", "text"})
    public void sendMDRatingPostSuccess() {
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields(PHYSICIAN_FNAME, PHYSICIAN_LNAME)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField(TEXT)
                    .sendPost()
                    .waitUntilNewPostisLoaded();

            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow());
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(FACILITY_NAME));
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(PHYSICIAN_FNAME + " " + PHYSICIAN_LNAME));
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect(TEXT));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"smoke", "negative"})
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName", "text"})
    public void sendMDRatingPostTestWoutFacilityName() {
        try {
            mdRatingOnMainPage
                    .fillPhysicianFields(PHYSICIAN_FNAME, PHYSICIAN_LNAME)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField(TEXT)
                    .sendPost();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mdRatingOnMainPage.errorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"negative"})
    //@Parameters({"facilityname", "physitianLastName", "text"})
    public void sendMDRatingPostWoutPhysitianFName() {
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields("", PHYSICIAN_LNAME)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField(TEXT)
                    .sendPost();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mdRatingOnMainPage.errorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"negative"})
    //@Parameters({"facilityname","physitianFirstName", "text"})
    public void sendMDRatingPostWoutPhysitianLName() {

        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields(PHYSICIAN_FNAME, "")
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField(TEXT)
                    .sendPost();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mdRatingOnMainPage.errorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"negative"})
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName"})
    public void sendMDRatingWithEmptyPost() {

        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields(PHYSICIAN_FNAME, PHYSICIAN_LNAME)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .sendPost();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mdRatingOnMainPage.errorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"negative"})
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName", "text"})
    public void sendMDRatingPostWoutRating() {

        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields(PHYSICIAN_FNAME, PHYSICIAN_LNAME)
                    .fillTextField(TEXT)
                    .sendPost();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mdRatingOnMainPage.errorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test (groups = {"positive"})
    //@Parameters({"facilityname","physitianFirstName", "physitianLastName", "text"})
    public void sendMDRating1LatterPost() {

        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(FACILITY_NAME)
                    .fillPhysicianFields(PHYSICIAN_FNAME, PHYSICIAN_LNAME)
                    .clickOnAllStarsTogether()
                    .rateItThree()              //Click on the third star
                    .fillTextField("p")
                    .sendPost()
                    .waitUntilNewPostisLoaded();

            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow());
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(FACILITY_NAME));
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(PHYSICIAN_FNAME + " " + PHYSICIAN_LNAME));
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect("p"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}

