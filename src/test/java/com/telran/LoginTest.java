package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ResetYourPasswordPage;
import junit.framework.Assert;
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

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;
/**
 * Created by Oleg on 30.05.2015.
 */
public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;
    public ResetYourPasswordPage resetYourPasswordPage;
    public MainPage mainPage;
    private boolean acceptNextAlert = true;
    String user="osh_il+4@yahoo.com";
    String pass="111111";
    String user1="osh_il+1@yahoo.com";

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);

    }
    @BeforeMethod
    public void beforeMethodSetUp() {
        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test(groups = {"smoke", "positive"})
    public void LoginSuccess() {

        try {
            loginPage
                    .fillEmailField("osh_il+4@yahoo.com")
                    .fillPasswordField("111111")
                    .clickOnLogin();
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.logOut();
            assertTrue("The Home Page doesn't open", homePage.isOnHomePage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginLogoutLogin() {

        loginPage
                .fillEmailField(user)
                .fillPasswordField(pass)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        mainPage.logOut();
        homePage.waitUntilHomePageIsLoaded();
        homePage.clickOnLogin();
        loginPage
                .waitUntilLoginPageIsLoaded()
                .fillEmailField(user1)
                .fillPasswordField(pass)
                .clickOnLogin();
        assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());

    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithoutAtInEmailField() {

        try {
            loginPage
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField("111111")
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid",loginPage.alertMessageInvalidEmail());
            assertTrue("The current page is changed",loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(groups = {"smoke", "negative"})
    public void LoginWithPasswordContains1Symbol() {

        try {
            loginPage
                    .fillEmailField("osh_il+4@yahoo.com")
                    .fillPasswordField("1")
                    .waitUntilAllertPasswordIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Password is valid",loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed",loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void ForgotPassword() {

        try {
            loginPage
                    .clickOnForgotPasswordLink();
            assertTrue("The Reset Password Page doesn't open",resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField("osh_il+4@yahoo.com");
            resetYourPasswordPage.clickOnEmailMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithEmptyFields() {

        try {
            loginPage
                    .fillEmailField("")
                    .fillPasswordField("")
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid",loginPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid",loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed",loginPage.isOnLoginPage());
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
