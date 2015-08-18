package com.telran;

import com.telran.pages.DocProfInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 05.06.2015.
 */
public class DocProfInfTest extends TestNgTestBase{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocProfInfPage docProfInfPage;
    private boolean acceptNextAlert = true;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);

        try {
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        try {
            Log.info("Opening Profile HCP page");
            if(profileDoctorPage.isOnProfileDoctorPage() == false) {
                docProfInfPage.clickOnDoneButton();
            }
//            Log.info("Wait for load Profile HCP page");
//            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnHealInf();
            Log.info("Wait for load DocProfInf page");
            Thread.sleep(4000);
            //docProfInfPage.waitUntilDocProfInfPageIsLoaded()
            //     .clearAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void EditProfInfSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            docProfInfPage
                    .fillSpecialtiesField("Oncology")
                    .clickOnAddSpecialtiesButton()
                    .fillSubspecialtiesField("insomnia")
                    .clickOnAddSubspecialtiesButton()
                    .fillTitlesField("doctor")
                    .clickOnAddTitlesButton()
                    .fillAreasField("Canavan")
                    .clickOnTooltipAreas()
                    .clickOnAddAreasButton()
                    .fillWorkPlacesNameField("assuta")
                    .fillWorkPlacesLocationField("a")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("all correct data added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void AddWorkPlaceInf() {
        Log.info("Checking that work place information added");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Ikhilov")
                    .fillWorkPlacesLocationField("Tel Aviv")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton();
            Assert.assertTrue(docProfInfPage.isWorkNameCorrect("Ikhilov"), "Work Name is not added");
            Assert.assertTrue(docProfInfPage.isWorkLocationCorrect("Tel Aviv-Yafo, Israel"), "Work Location is not added");
            docProfInfPage.clickOnDoneButton();
            Reporter.log("work place information added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptyFields() {
        Log.info("Checking that all empty fields added");
        try {
            docProfInfPage
                    .fillSpecialtiesField("")
                    .fillSubspecialtiesField("")
                    .fillTitlesField("")
                    .fillAreasField("")
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("")
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
            Reporter.log("all empty fields added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
     public void AddEmptySpecialties() {
        Log.info("Checking that Specialties with empty field cannot added");
        try {
            docProfInfPage
                    .fillSpecialtiesField("");
            Assert.assertTrue(docProfInfPage.isAddSpecButtonDisabled(), "The Add Specialties Button is Enabled");
            Reporter.log("Specialties Button disnable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void AddDelSpecialties() {
        Log.info("Checking that Specialties added and deleted");
        try {
            docProfInfPage
                    .fillSpecialtiesField("abcd")
                    .clickOnAddSpecialtiesButton()
                    .clickOnDelSpecButton()
                    .clickOnConfSpecButton();
            Assert.assertTrue(docProfInfPage.isSpecExists(), "The specialty exists");
            Reporter.log("Specialties added and deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddSpecialtiesWithoutAddButton() {
        Log.info("Checking that added Specialties are not published");
        try {
            docProfInfPage
                    .fillSpecialtiesField("efgh")
                    .clickOnDoneButton();
            Assert.assertFalse(profileDoctorPage.isSpecialtiesExist(), "The specialty published");
            Reporter.log("Specialties are not published");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void AddSpecialtiesWithAddButton() {
        Log.info("Checking that added Specialties are published");
        try {
            docProfInfPage
                    .fillSpecialtiesField("abcd")
                    .clickOnAddSpecialtiesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isSpecialtiesExist(), "The specialty unpublished");
            Reporter.log("Specialties are published");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptyLocationWPandFillNameWPFields() {
        Log.info("Checking that Work Place Name added and Work Place Location not");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(), "The button add work place is clickable");
            Reporter.log("The button add work place is not clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddEmptyLocationWPandNameWPFields() {
        Log.info("Checking that Work Place Name and Work Place Location do not added ");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(), "The button add work place is clickable");
            Reporter.log("The button add work place is not clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void DeleteLocationWPandNameWP() {
        Log.info("Checking that Work Place Name and Work Place Location added and deleted");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta");
            docProfInfPage
                    .fillWorkPlacesLocationField("t")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDelWorkPlacesButton()
                    .clickOnConfWorkPlacesButton();
            Assert.assertTrue(docProfInfPage.isLocationWPExists(), "Location is not disappear");
            Reporter.log("Work Place Name and Work Place Location added and deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sleep (){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
