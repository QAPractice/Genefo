package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ResetYourPasswordPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private static String USER ="osh_il+4@yahoo.com";
    private static String PASSWORD ="111111";

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        resetYourPasswordPage = PageFactory.initElements(driver, ResetYourPasswordPage.class);
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
//        if(mainPage.isMyHomeExists())
//            mainPage.logOut();
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
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
            mainPage.logOut();
            homePage.waitUntilHomePageIsLoaded();
            //assertTrue("The Home Page doesn't open", homePage.isOnHomePage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginLogoutLogin() {
        String user1="osh_il+1@yahoo.com";
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
                .fillEmailField(user1)
                .fillPasswordField(PASSWORD)
                .clickOnLogin();
        mainPage.waitUntilMainPageIsLoaded();
        assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
        mainPage.logOut();
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithoutAtInEmailField() {

        try {
            loginPage
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField(PASSWORD)
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
                    .fillEmailField(USER)
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
            resetYourPasswordPage.waitUntilResetPageIsLoaded();
            assertTrue("The Reset Password Page doesn't open", resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField(USER);
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
