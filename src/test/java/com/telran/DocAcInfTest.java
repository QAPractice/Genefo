package com.telran;

import com.telran.pages.DocAcInfPage;
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
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;
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
    public String EmailNickname; // Keeps the part of email before sign @
    private boolean acceptNextAlert = true;

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
            loginPage.login("osh_il+4@yahoo.com","111111");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
            profileDoctorPage.clickOnEditAccInf();
            docAcInfPage.waitUntilDocAcInfPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //positive
    @Test
    public void EditAccInfSuccess() {

        try {
            EmailNickname = randomAlphabetic(5);
            docAcInfPage
                    .fillPasswordField("111111")
                    .fillEmailField("one" + EmailNickname + "@usgenefo.com")
                    .clickOnSaveButton();
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ClickOnCancel() {

        try {
            assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //negative
    @Test
    public void EditAccInfEmptyFiels() {

        try {
            docAcInfPage
                    .fillPasswordField("")
                    .fillEmailField("")
                    .clickOnSaveButton();
            assertTrue(docAcInfPage.alertMessageInvalidEmail());
            assertTrue(docAcInfPage.alertMessageInvalidPassword());
            assertTrue(docAcInfPage.isOnDocAcInfPage());
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
