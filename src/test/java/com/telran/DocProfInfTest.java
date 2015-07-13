package com.telran;

import com.telran.pages.DocProfInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 05.06.2015.
 */
public class DocProfInfTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocProfInfPage docProfInfPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        //this.driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docProfInfPage = PageFactory.initElements(driver, DocProfInfPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login("osh_il+4@yahoo.com", "111111");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            Log.info("Opening Profile HCP page");
            if(profileDoctorPage.isOnProfileDoctorPage() == false) {
                docProfInfPage.clickOnDoneButton();
            }
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnHealInf();
            Log.info("Wait for load DocProfInf page");
            docProfInfPage.waitUntilDocProfInfPageIsLoaded();
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
                    .fillWorkPlacesLocationField("J")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(),"Profile HCP Page doesn't open");
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
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(),"Profile HCP Page doesn't open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
     public void AddEmptySpecialties() {
        Log.info("Checking that Specialties empty field added");
        try {
            docProfInfPage
                    .fillSpecialtiesField("");
            Assert.assertTrue(docProfInfPage.isAddSpecButtonExists()==false,"The Add Specialties Button Enable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void AddDelSpecialties() {
        Log.info("Checking that Specialties added and deleted");
        try {
            docProfInfPage
                    .fillSpecialtiesField("abcd")
                    .clickOnAddSpecialtiesButton()
                    .clickOnDelSpecButton()
                    .clickOnConfSpecButton();
            Assert.assertTrue(docProfInfPage.isSpecExists(),"The specialty exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandFillNameWPFields() {
        Log.info("Checking that Work Place Name added and Work Place Location not");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(),"The button add work place is clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"negative"})
    public void AddEmptyLocationWPandNameWPFields() {
        Log.info("Checking that Work Place Name and Work Place Location do not added ");
        try {
            docProfInfPage
                    .fillWorkPlacesNameField("")
                    .fillWorkPlacesLocationField("");
            Assert.assertTrue(docProfInfPage.isAddWorkPlacesDisButtonExists(),"The button add work place is clickable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void DeleteLocationWPandNameWP() {
        Log.info("Checking that Work Place Name and Work Place Location added and deleted");
        try {
            sleep();
            docProfInfPage
                    .fillWorkPlacesNameField("Assuta");
            //sleep();
            docProfInfPage
                    .fillWorkPlacesLocationField("t")
                    .clickOnTooltipWP()
                    .clickOnAddWorkPlacesButton()
                    .clickOnDelWorkPlacesButton()
                    .clickOnConfWorkPlacesButton();
            Assert.assertTrue(docProfInfPage.isLocationWPExists(),"Location is not disappear");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void DeleteAllDataIfExist() {
        Log.info("Checking that All Data deleted");
        try {
            sleep();
                    if (docProfInfPage.isSpecExists()) {
                        docProfInfPage
                                .clickOnDelSpecButton()
                                .clickOnConfSpecButton();
                    }
                    if (docProfInfPage.isSubspecExists()) {
                        docProfInfPage
                                .clickOnDelSubspecButton()
                                .clickOnConflSubspecButton();
                    }
                    if (docProfInfPage.isTitlesExists()) {
                        docProfInfPage
                                .clickOnDelTitleButton()
                                .clickOnConfTitleButton();
                    }
                    if (docProfInfPage.isAreasExists()) {
                        docProfInfPage
                                .clickOnDelAreasButton()
                                .clickOnConfAreasButton();
                    }
                    if (docProfInfPage.isLocationWPExists()) {
                        docProfInfPage
                                .clickOnDelWorkPlacesButton()
                                .clickOnConfWorkPlacesButton();
                    }

            docProfInfPage
                    .clickOnDoneButton();
            Assert.assertTrue(profileDoctorPage.isOnProfileDoctorPage(), "Profile HCP Page doesn't open");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
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
