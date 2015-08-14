package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.PublicProfilePage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class FollowingUserTest extends TestNgTestBase {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    MainPage mainPage;
    PublicProfilePage publicProfilePage;
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @BeforeClass (alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");
        //this.driver = new FirefoxDriver();
        //wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        publicProfilePage = PageFactory.initElements(driver, PublicProfilePage.class);

        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("ri-lopatina@yandex.ru", "111111");
            mainPage.waitUntilMainPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (groups = {"smoke", "positive"}, description = "addFollowSuccessFromConnectPeopleConditionField")
    public void addFollowSuccessFromConnectPeopleConditionField(){
        Reporter.log("AddFollowSuccessFromConnectPeopleConditionField test");
        Log.info("AddFollowSuccessFromConnectPeopleConditionField test");
        mainPage.isOnMainPage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPage.openConnectPeopleThisConditionProfile();
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        Reporter.log("New profile was added to following successfully from ConnectPeopleThisConditionProfile");
    }

    @Test (groups = {"smoke", "positive"}, description = "unFollowSuccess")
    public void unFollowSuccess(){
        Reporter.log("UnFollowSuccess test");
        Log.info("UnFollowSuccess test");
        mainPage.isOnMainPage();
        String name = mainPage.getFollowName();
        mainPage.openFollow();
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publicProfilePage.removeFollow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(publicProfilePage.plusFollowPanel());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertFalse(mainPage.isFollowingNamePresents(name));
        Reporter.log("New profile was unfollowed successfully");
    }
    @Test (groups = {"smoke", "positive"}, description = "addFollowSuccessFromPosts")
    public void addFollowSuccessFromPosts(){
        Reporter.log("AddFollowSuccessFromPosts test");
        Log.info("AddFollowSuccessFromPosts test");
        mainPage.isOnMainPage();
        mainPage.fillSet();
        mainPage.addMyUserNameToFillSet();
        if (!mainPage.addNewFollowerFromPost())
            return;
        publicProfilePage.isOnPublicProfilePage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertTrue(mainPage.isFollowingNamePresents(name));
        Reporter.log("New profile was added to follow successfully from posts");

}

}