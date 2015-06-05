package com.telran;

import com.telran.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class FollowingDoctorTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    MainPage mainPage;
    PublicProfilePage publicProfilePage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        publicProfilePage = PageFactory.initElements(driver, PublicProfilePage.class);

        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("ri-lopatina1@yandex.ru", "123456");
            mainPage.waitUntilMainPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (groups = {"smoke", "positive"})
    public void addFollowSuccessFromConnectPeopleConditionField(){
        mainPage.isOnMainPage();
        mainPage.chooseConditionForDoctor("Insomnia");
        mainPage.chooseConditionFromDropDown();
        mainPage.clickViewButton();
        mainPage.openConnectPeopleThisConditionProfile();
        publicProfilePage.isOnPublicProfilePage();
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(mainPage.isFollowingNamePresents(name));
    }

    @Test (groups = {"smoke", "positive"})
    public void unFollowSuccess(){
        mainPage.isOnMainPage();
        mainPage.chooseConditionForDoctor("Insomnia");
        mainPage.chooseConditionFromDropDown();
        mainPage.clickViewButton();
        String name = mainPage.getFollowName();
        mainPage.openFollow();
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publicProfilePage.removeFollow();
        assertTrue(publicProfilePage.plusFollowPanel());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertFalse(mainPage.isFollowingNamePresents(name));
    }
    @Test (groups = {"smoke", "positive"})
    public void addFollowSuccessFromPosts(){
        mainPage.isOnMainPage();
        mainPage.chooseConditionForDoctor("Insomnia");
        mainPage.chooseConditionFromDropDown();
        mainPage.clickViewButton();
        mainPage.openPostNameLink();
        publicProfilePage.isOnPublicProfilePage();
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertTrue(mainPage.isFollowingNamePresents(name));
    }
    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}