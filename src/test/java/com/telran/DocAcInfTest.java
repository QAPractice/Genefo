package com.telran;

import com.telran.pages.ProfileDoctorPage;
import com.telran.pages.DocAcInfPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.Reporter.log;

/**
 * Created by Oleg on 31.05.2015.
 */
public class DocAcInfTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocAcInfPage docAcInfPage;
    private boolean acceptNextAlert = true;
    public String EmailNickname; // Keeps the part of email before sign @
    private String EMAIL1 = "osh_il+19@yahoo.com";
    private String EMAIL2 = "osh_il+18@yahoo.com";

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docAcInfPage = PageFactory.initElements(driver, DocAcInfPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login(EMAIL1, LoginTest.PASSWORD);
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
            profileDoctorPage.clickOnEditAccInf();
            Log.info("Wait for load DocAcInf page");
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"})
    public void UpdateEditAccInfSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            docAcInfPage
                    .fillPasswordField(LoginTest.PASSWORD)
                    .fillEmailField(EMAIL2)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(LoginTest.PASSWORD)
                    .clickOnCurSaveButton();
            assertTrue("Alert1", docAcInfPage.alertMessageAccountSuccess());
            mainPage.selectMyAccount();
            Log.info("Wait for load Profile HCP page");
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            Log.info("Wait for load DocAcInf page");
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
            docAcInfPage
                    .fillPasswordField(LoginTest.PASSWORD)
                    .fillEmailField(EMAIL1)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(LoginTest.PASSWORD)
                    .clickOnCurSaveButton();
            //assertTrue("Alert2", docAcInfPage.alertMessageAccountSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void ClickOnCancel() {
        Log.info("Checking that operation is canceled");
        try {
            docAcInfPage
                    .clickOnCancel();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void EditAccInfEmptyFiels() {
        Log.info("Checking that empty fields are not updated");
        try {
            docAcInfPage
                    .fillEmailField("")
                    .fillPasswordField("")
                    .clickOnTitle();
            assertTrue("The Email is valid", docAcInfPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid",docAcInfPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", docAcInfPage.isOnDocAcInfPage());
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
