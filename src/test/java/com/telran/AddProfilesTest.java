package com.telran;

import com.telran.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 5/19/2015.
 */
public class AddProfilesTest {
    public WebDriver driver;
    public WebDriverWait wait;
    MyProfilesPage myProfilesPage;
    ProfilePage profilePage;
    LoginPage loginPage;
    MainPage mainPage;
    SummaryPage summaryPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        myProfilesPage = PageFactory.initElements(driver, MyProfilesPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        summaryPage = PageFactory.initElements(driver, SummaryPage.class);

        try {
            loginPage.openLoginPage()
                    .isOnLoginPage();
            loginPage.fillEmailField("ri-lopatina@yandex.ru")
                    .fillPasswordField("123456")
                    .clickToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddProfileSuccess() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.waitUntilProfilePageIsLoaded();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField("AAAAA");
        profilePage.fillProfileLastNameField("BBBBBB");
        profilePage.selectProfilePatient("2");
        profilePage.isPatientSelected("Friend");
        profilePage.selectGender("0");
        profilePage.isGenderSelected("Male");
        profilePage.fillProfileConditionField("Alstrom");
        profilePage.autoFillCondition();
        profilePage.selectMonth("6");
        profilePage.isMonthSelected("July");
        profilePage.selectDay("0");
        profilePage.isDaySelected("1");
        profilePage.selectYear("5");
        profilePage.isYearSelected("2010");
        profilePage.selectDiagnosYear("1");
        profilePage.isDiagnosYearSelected("2014");
        profilePage.clickToSubmit();
        assertTrue(summaryPage.isOnSummaryPage());

        summaryPage.clickOnHome();
    }
    //  Negative tests
   @Test      //Bug!!!
    public void AddProfileWithoutCondition() {
         mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.waitUntilProfilePageIsLoaded();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField("AAAAA");
        profilePage.fillProfileLastNameField("BBBBBB");
        profilePage.selectProfilePatient("2");
        profilePage.isPatientSelected("Friend");
        profilePage.selectGender("0");
        profilePage.isGenderSelected("Male");
        profilePage.selectMonth("6");
        profilePage.isMonthSelected("July");
        profilePage.selectDay("0");
        profilePage.isDaySelected("1");
        profilePage.selectYear("5");
        profilePage.isYearSelected("2010");
        profilePage.selectDiagnosYear("1");
        profilePage.isDiagnosYearSelected("2014");
        profilePage.clickToSubmit();
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       assertTrue(profilePage.isOnProfilePage());
    }
    @Test
    public void AddProfileWithoutFirstName() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.waitUntilProfilePageIsLoaded();
        profilePage.isOnProfilePage();
        profilePage.fillProfileLastNameField("BBBBBB");
        profilePage.selectProfilePatient("2");
        profilePage.isPatientSelected("Friend");
        profilePage.selectGender("0");
        profilePage.isGenderSelected("Male");
        profilePage.fillProfileConditionField("Alstrom");
        profilePage.autoFillCondition();
        profilePage.selectMonth("6");
        profilePage.isMonthSelected("July");
        profilePage.selectDay("0");
        profilePage.isDaySelected("1");
        profilePage.selectYear("5");
        profilePage.isYearSelected("2010");
        profilePage.selectDiagnosYear("1");
        profilePage.isDiagnosYearSelected("2014");
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(profilePage.isOnProfilePage());
    }
}
