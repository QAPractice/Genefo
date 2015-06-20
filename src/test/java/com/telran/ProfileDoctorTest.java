package com.telran;

import com.telran.pages.ProfileDoctorPage;
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
 * Created by Oleg on 20.06.2015.
 */
public class ProfileDoctorTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProfileDoctorPage profileDoctorPage;
    private boolean acceptNextAlert = true;
    public String EmailNickname; // Keeps the part of email before sign @

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            loginPage.login("osh_il+4@yahoo.com","111111");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.selectMyAccount();
            profileDoctorPage.waitUntilProfileDoctorPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void DiscoverYourHomePage() {

        try {
            profileDoctorPage.clickOnDisYourHP();
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
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