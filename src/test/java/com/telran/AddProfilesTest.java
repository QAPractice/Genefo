package com.telran;

import com.telran.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Л on 5/19/2015.
 */
public class AddProfilesTest {
    public WebDriver driver;
    public WebDriverWait wait;
    MyProfilesPage myProfilesPage;
    ProfilePage profilePage;
    LoginPage loginPage;
    HomePage homePage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        myProfilesPage = PageFactory.initElements(driver, MyProfilesPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);

        try {
            loginPage.openLoginPage();
                 //   .isOnLoginPage();
            loginPage.fillEmailField("ri-lopatina@yandex.ru")
                    .fillPasswordField("123456")
                    .clickToLogin();
            homePage.isOnHomePage();
            homePage.selectMyProfile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddProfileSuccess() {
//        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.waitUntilProfilePageIsLoaded();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField("AAAAA");
        profilePage.fillProfileLastNameField("123456");
        profilePage.selectProfilePatient("2");
   //     profilePage.isPatientSelected("Friend");
        profilePage.fillProfileConditionField("Alstrom");
        profilePage.autoFillCondition("Alstrom syndrome");
        profilePage.selectGender("Male");


    }
}
