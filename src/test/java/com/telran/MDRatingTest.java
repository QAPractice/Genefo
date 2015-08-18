package com.telran;

import com.telran.pages.DataProviders;
import com.telran.pages.LoginPage;
import com.telran.pages.MDRatingOnMainPage;
import com.telran.pages.MainPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/30/2015.
 */
public class MDRatingTest extends TestNgTestBase {
    private static String EMAIL = "ri-lopatina@yandex.ru";
    private static String PASSWORD = "111111";
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MDRatingOnMainPage mdRatingOnMainPage;

    @BeforeClass (alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");
        //this.driver = new FirefoxDriver();
        // wait = new WebDriverWait(driver, 5);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        mdRatingOnMainPage = PageFactory.initElements(driver, MDRatingOnMainPage.class);

        try {
            loginPage.openLoginPage();
            loginPage.isOnLoginPage();
            loginPage.fillEmailField(EMAIL)
                    .fillPasswordField(PASSWORD)
                    .clickOnLogin();
            mainPage.waitUntilMainPageIsLoaded()
                    .openMDRatingButtonPanel();
            mdRatingOnMainPage.isOnMDRatingPanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test (groups = {"smoke", "positive"}, description = "sendMDRatingPostSuccess", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostSuccess(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("SendMDRatingPostSuccess test");
        Log.info("SendMDRatingPostSuccess test");
            int number = Integer.parseInt(starNumber);
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost()
                    .waitUntilNewPostisLoaded();
            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow(number), "Matched star is not yellow");
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(facility_name), "Facility name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(physician_fname + " " + physician_lname), "Physician name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect(text), "Something wrong with post");

        Reporter.log("MDRating post was sent successfully");
    }

        @Test (groups = {"smoke", "negative"}, description = "sendMDRatingPostTestWithoutFacilityName", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostTestWithoutFacilityName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
            Reporter.log("sendMDRatingPostTestWithoutFacilityName test");
            Log.info("sendMDRatingPostTestWithoutFacilityName test");
            int number = Integer.parseInt(starNumber);

            mdRatingOnMainPage
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
            try {
                mainPage.waitForErrorMessage();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());

            Reporter.log("Post is not sent");
    }

    @Test(groups = {"smoke", "negative"}, description = "sendMDRatingPostWithoutPhysicianFName", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutPhysicianFName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutPhysicianFName test");
        Log.info("sendMDRatingPostWithoutPhysicianFName test");
        int number = Integer.parseInt(starNumber);

            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields("", physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
        try {
            mainPage.waitForErrorMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("No alert message", mainPage.getRequiredFieldsMessage());

        Reporter.log("Post is not sent");
    }

    @Test(groups = {"smoke", "negative"}, description = "sendMDRatingPostWithoutPhysicianLName", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutPhysicianLName(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutPhysicianLName test");
        Log.info("sendMDRatingPostWithoutPhysicianLName test");
        int number = Integer.parseInt(starNumber);

            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, "")
                    .clickOnAnyStar(number)
                    .fillTextField(text)
                    .sendPost();
        try {
            mainPage.waitForErrorMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("No alert message", mainPage.getRequiredFieldsMessage());

        Reporter.log("Post is not sent");
    }

    /*@Test (groups = {"negative"}, description = "sendMDRatingWithEmptyPost", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingWithEmptyPost(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        int number = Integer.parseInt(starNumber);
        try {
            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .sendPost();
            mainPage.waitForErrorMessage();
            assertTrue("No alert message", mainPage.getRequiredFieldsMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Post is not sent");
    }*/
    @Test(groups = {"smoke", "negative"}, description = "sendMDRatingPostWithoutRating", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRatingPostWithoutRating(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRatingPostWithoutRating test");
        Log.info("sendMDRatingPostWithoutRating test");
        int number = Integer.parseInt(starNumber);

            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .fillTextField(text)
                    .sendPost();
        try {
            mainPage.waitForErrorMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("No alert message", mainPage.getRequiredFieldsMessage());

        Reporter.log("Post is not sent");
    }
   /* @Test (groups = {"positive"}, description = "sendMDRating1LatterPost", dataProviderClass = DataProviders.class, dataProvider = "loadDataForMDRating")
    public void sendMDRating1LatterPost(String facility_name, String physician_fname, String physician_lname, String starNumber, String text) {
        Reporter.log("sendMDRating1LatterPost test");
        Log.info("sendMDRating1LatterPost test");
        int number = Integer.parseInt(starNumber);

            mdRatingOnMainPage
                    .fillMedicalFacilityField(facility_name)
                    .fillPhysicianFields(physician_fname, physician_lname)
                    .clickOnAnyStar(number)
                    .fillTextField("p")
                    .sendPost()
                    .waitUntilNewPostisLoaded();

            Assert.assertTrue(mdRatingOnMainPage.isThirdStarYellow(number), "Matched star is not yellow");
            Assert.assertTrue(mdRatingOnMainPage.isFacilityNameCorrect(facility_name), "Facility name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isPhysicianNameCorrect(physician_fname + " " + physician_lname), "Physician name is not correct");
            Assert.assertTrue(mdRatingOnMainPage.isTextCorrect("p"), "Something wrong with the post");

        Reporter.log("1 latter post was sent successfully");
    }*/

}

