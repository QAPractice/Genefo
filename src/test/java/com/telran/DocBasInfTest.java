package com.telran;

import com.telran.pages.DocBasInfPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Oleg on 03.06.2015.
 */
public class DocBasInfTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    public DocBasInfPage docBasInfPage;
    private boolean acceptNextAlert = true;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        docBasInfPage = PageFactory.initElements(driver, DocBasInfPage.class);
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
            if(profileDoctorPage.isOnProfileDoctorPage() == false) {
                mainPage.selectMyAccount();
            }
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditBasInf();
            docBasInfPage.waitUntilDocBasInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"})
    public void EditBasicInfSuccess() {

        try {
            docBasInfPage
                    .fillFirstNameField("Doctor")
                    .fillLastNameField("House")
                    .fillLocationField("afr")
                    .clickOnTooltip()
                    .clickOnSaveButton();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            assertTrue("Profile HCP Page doesn't open", profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void ClickOnCancel() {

        try {
            docBasInfPage
                    .clickOnCancel();
            assertTrue("Profile HCP Page doesn't open",profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void EditBasicInfEmptyFiels() {

        try {
            docBasInfPage
                    .fillFirstNameField("")
                    .fillLastNameField("")
                    .fillLocationField("");
            assertTrue("The First Name is valid", docBasInfPage.alertMessageInvalidFirstName());
            assertTrue("The Last Name is valid",docBasInfPage.alertMessageInvalidLastName());
            assertTrue("The current page is changed",docBasInfPage.isOnDocBasInfPage());
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
