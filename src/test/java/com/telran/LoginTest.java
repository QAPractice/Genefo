package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.ResetYourPasswordPage;
import com.telran.pages.MainPage;
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
 * Created by Oleg on 30.05.2015.
 */
public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public ResetYourPasswordPage resetYourPasswordPage;
    public MainPage mainPage;
    private boolean acceptNextAlert = true;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver,LoginPage.class);

        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded();
            } catch (Exception e) {
              e.printStackTrace();
              }
    }

    @Test
     public void LoginSuccess() {

        try {
            loginPage
                    .fillEmailField("osh_il+4@yahoo.com")
                    .fillPasswordField("111111")
                    .clickOnLogin();
            assertTrue(mainPage.isOnMainPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void LoginWithNotValidEmail() {

        try {
            loginPage
                    .fillEmailField("osh_il+4yahoo.com")
                    .fillPasswordField("111111")
                    .clickOnLogin();
            assertTrue(loginPage.alertMessageInvalidEmail());
            assertTrue(loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void LoginWithNotValidPassword() {

        try {
            loginPage
                    .fillEmailField("osh_il+4@yahoo.com")
                    .fillPasswordField("11111")
                    .clickOnLogin();
            assertTrue(loginPage.alertMessageInvalidPassword());
            assertTrue(loginPage.isOnLoginPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ForgotPassword() {

        try {
            loginPage
                    .clickOnForgotPasswordLink();
            assertTrue(resetYourPasswordPage.isOnResetPage());
            resetYourPasswordPage.fillEmailField("osh_il+4@yahoo.com");
            resetYourPasswordPage.clickOnEmailMe();
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
