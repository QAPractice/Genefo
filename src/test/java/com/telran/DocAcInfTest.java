package com.telran;

import com.telran.pages.ProfileDoctorPage;
import com.telran.pages.DocAcInfPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
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
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocAcInfPage docAcInfPage;
    private boolean acceptNextAlert = true;
    public String EmailNickname; // Keeps the part of email before sign @
    private static String PASSWORD ="111111";
    private static String EMAIL2 = "osh_il+15@yahoo.com";
    private static String EMAIL1 = "osh_il+14@yahoo.com";

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
            loginPage.login(EMAIL1,PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            if(profileDoctorPage.isOnProfileDoctorPage() == false) {
                mainPage.selectMyAccount();
            }
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"})
    public void EditAccInfSuccess() {

        try {
            docAcInfPage
                    .fillPasswordField(PASSWORD)
                    .fillEmailField(EMAIL1)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(PASSWORD)
                    .clickOnCurSaveButton();
            assertTrue("Alert1", docAcInfPage.alertMessageAccountSuccess());
            mainPage.selectMyAccount();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
            docAcInfPage
                    .fillPasswordField(PASSWORD)
                    .fillEmailField(EMAIL2)
                    .clickOnSaveButton()
                    .waitUntilEnterYourCurrentPassIsLoaded()
                    .fillCurrentPasswordField(PASSWORD)
                    .clickOnCurSaveButton();
            //assertTrue("Alert2", docAcInfPage.alertMessageAccountSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test(groups = {"smoke", "positive"})
//    public void UpdateEditAccInfSuccess() {
//
//        try {
//            docAcInfPage
//                    .fillPasswordField(PASSWORD)
//                    .fillEmailField(EMAIL2)
//                    .clickOnSaveButton()
//                    .waitUntilEnterYourCurrentPassIsLoaded()
//                    .fillCurrentPasswordField(PASSWORD)
//                    .clickOnCurSaveButton();
//            assertTrue("Alert",docAcInfPage.alertMessageAccountSuccess());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test(groups = {"smoke", "positive"})
    public void ClickOnCancel() {

        try {
            docAcInfPage
                    .clickOnCancel();
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void EditAccInfEmptyFiels() {

        try {
            docAcInfPage
                    .fillPasswordField("")
                    .fillEmailField("")
                    .clickOnSaveButton();
            assertTrue("The Email is valid",docAcInfPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid",docAcInfPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed",docAcInfPage.isOnDocAcInfPage());
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
}
