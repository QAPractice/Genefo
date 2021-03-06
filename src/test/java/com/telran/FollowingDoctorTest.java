package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.ProfileDoctorPage;
import com.telran.pages.PublicProfilePage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Л on 6/2/2015.
 */
public class FollowingDoctorTest extends TestNgTestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    LoginPage loginPage;
    MainPage mainPage;
    PublicProfilePage publicProfilePage;
    ProfileDoctorPage profileDoctorPage;

    @BeforeClass (alwaysRun = true)
    public void setup() {
        PropertyConfigurator.configure("log4j.properties");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        profileDoctorPage = PageFactory.initElements(driver, ProfileDoctorPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        publicProfilePage = PageFactory.initElements(driver, PublicProfilePage.class);
        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("gjgfytf@jhghtf.com", "123456");
            profileDoctorPage.isOnProfileDoctorPage();
            profileDoctorPage.clickOnDisYourHP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPage.isOnMainPage();
        mainPage.chooseConditionForDoctor("Alstrom");
        mainPage.chooseConditionFromDropDown();
        mainPage.clickViewButton();
    }
    @Test (groups = {"smoke", "positive"}, description = "addFollowSuccessFromConnectPeopleConditionField")
    public void addFollowSuccessFromConnectPeopleConditionField(){
        Reporter.log("AddFollowSuccessFromConnectPeopleConditionField test");
        Log.info("1.AddFollowSuccessFromConnectPeopleConditionField test");
        mainPage.openConnectPeopleThisConditionProfile();
        publicProfilePage.isOnPublicProfilePage();
        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertTrue(mainPage.isFollowingNamePresents(name));
        Reporter.log("1.New profile was added to following successfully from ConnectPeopleThisConditionProfile");
    }

    @Test (groups = {"smoke", "positive"}, description = "unFollowSuccess")
    public void unFollowSuccess(){
        Reporter.log("UnFollowSuccess test");
        Log.info("2.UnFollowSuccess test");
        String name = mainPage.getFollowName();
        mainPage.openFollow();
        publicProfilePage.isOnPublicProfilePage();

        publicProfilePage.removeFollow();

        assertTrue(publicProfilePage.plusFollowPanel());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertFalse(mainPage.isFollowingNamePresents(name));
        Reporter.log("2.New profile was unfollowed successfully");
    }
    @Test (groups = {"smoke", "positive"}, description = "addFollowSuccessFromPosts")
    public void addFollowSuccessFromPosts(){
        Reporter.log("AddFollowSuccessFromPosts test");
        Log.info("3.AddFollowSuccessFromPosts test");
        mainPage.fillSet();
        mainPage.addMyDoctorNameToFillSet();
        if (!mainPage.addNewFollowerFromPost())
            return;
        publicProfilePage.isOnPublicProfilePage();

        String name = publicProfilePage.getPublicProfileName();
        publicProfilePage.addFollow();
        assertTrue(publicProfilePage.isUnFollowPanelOnPage());
        publicProfilePage.clickOnHome();
        mainPage.isOnMainPage();
        assertTrue(mainPage.isFollowingNamePresents(name));
        Reporter.log("3.New profile was added to follow successfully from posts");
    }

}