package com.telran;

import com.telran.pages.DocBasInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 03.06.2015.
 */
public class DocBasInfTest extends TestNgTestBase{
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocBasInfPage docBasInfPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docBasInfPage = PageFactory.initElements(driver, DocBasInfPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
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
                mainPage.selectMyAccount();
            }
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditBasInf();
            Log.info("Wait for load DocBasInf page");
            docBasInfPage.waitUntilDocBasInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"})
    public void EditBasicInfSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            docBasInfPage
                    .fillFirstNameField("Doctor")
                    .fillLastNameField("House")
                    .fillLocationField("afr")
                    .clickOnTooltip()
                    .clickOnSaveButton();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            Reporter.log("all correct data added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void ClickOnCancel() {
        Log.info("Checking that operation is canceled");
        try {
            docBasInfPage
                    .clickOnCancel();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
            Reporter.log("operation is canceled successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void EditBasicInfEmptyFiels() {
        Log.info("Checking that empty fields are not updated");
        try {
            docBasInfPage
                    .fillFirstNameField("")
                    .fillLastNameField("")
                    .fillLocationField("")
                    .clickOnSaveButton();
            assertTrue("The First Name is valid", docBasInfPage.alertMessageInvalidFirstName());
            assertTrue("The Last Name is valid",docBasInfPage.alertMessageInvalidLastName());
            assertTrue("The current page is changed",docBasInfPage.isOnDocBasInfPage());
            Reporter.log("empty fields are not updated successful");
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
