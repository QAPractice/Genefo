package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ResetYourPasswordPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 30.05.2015.
 */
public class LoginTest extends TestNgTestBase{
    public static String USER = "osh_il+4@yahoo.com";
    public static String PASSWORD = "111111";
    public static String USER1 = "osh_il+1@yahoo.com";
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public HomePage homePage;
    public LoginPage loginPage;
    public ResetYourPasswordPage resetYourPasswordPage;
    public MainPage mainPage;

    @BeforeClass(groups = {"smoke"}, alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        PropertyConfigurator.configure("log4j.properties");

        homePage = new HomePage(driver);
        resetYourPasswordPage = new ResetYourPasswordPage(driver);
    }

    @BeforeMethod(groups = {"smoke"}, alwaysRun = true)
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
        Log.info("Checking that all correct data added successfully");
        try {
            loginPage
                    .fillEmailField(USER)
                    .fillPasswordField(PASSWORD)
                    .clickOnLogin();
            mainPage.waitUntilMainPageIsLoaded();
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
            mainPage.logOut();
            homePage.waitUntilHomePageIsLoaded();
            //assertTrue("The Home Page doesn't open", homePage.isOnHomePage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Login successful");
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginLogoutLogin() {
        Log.info("Checking ability login ,logout and login again with another user");
        try {
        loginPage
                .fillEmailField(USER)
                .fillPasswordField(PASSWORD)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        mainPage.logOut();
        homePage.waitUntilHomePageIsLoaded();
        homePage.clickOnLogin();
        loginPage
                .waitUntilLoginPageIsLoaded()
                .fillEmailField(USER1)
                .fillPasswordField(PASSWORD)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
        mainPage.logOut();
        homePage.waitUntilHomePageIsLoaded();
        }catch (Exception e){
            e.printStackTrace();
        }
        Reporter.log("Login successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithoutAtInEmailField() {
        Log.info("Checking inability lodin without @ in email field");
        try {
            loginPage
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField(PASSWORD)
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid", loginPage.alertMessageInvalidEmail());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }
    @Test(groups = {"smoke", "negative"})
    public void LoginWithPasswordContains1Symbol() {
        Log.info("Checking inability lodin with password contains 1 symbol");
        try {
            loginPage
                    .fillEmailField(USER)
                    .fillPasswordField("1")
                    .waitUntilAllertPasswordIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Password is valid", loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }

    @Test(groups = {"smoke", "positive"})
    public void ForgotPassword() {
        Log.info("Checking ability recreate password");
        try {
            loginPage
                    .clickOnForgotPasswordLink();
            resetYourPasswordPage.waitUntilResetPageIsLoaded();
            assertTrue("The Reset Password Page doesn't open", resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField(USER);
            resetYourPasswordPage.clickOnEmailMe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Password recreated successful");
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithEmptyFields() {
        Log.info("Checking inability lodin with empty fields");
        try {
            loginPage
                    .fillEmailField("")
                    .fillPasswordField("")
                    .waitUntilAllertEmailIsLogIsLoaded()
                    .clickOnLogin();
            assertTrue("The Email is valid", loginPage.alertMessageInvalidEmail());
            assertTrue("The Password is valid", loginPage.alertMessageInvalidPassword());
            assertTrue("The current page is changed", loginPage.isOnLoginPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }


}
