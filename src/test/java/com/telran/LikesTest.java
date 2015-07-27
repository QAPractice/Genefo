package com.telran;

import com.telran.pages.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Yura 10-July-15.
 */
public class LikesTest {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    LikesPage likesPage;
    MainPage mainPage;
    PostOnMainPage postOnMainPage;

    @BeforeTest
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        likesPage = PageFactory.initElements(driver, LikesPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        postOnMainPage = PageFactory.initElements(driver, PostOnMainPage.class);
        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("jakoff+444@gmail.com", "111111");
            mainPage.waitUntilMainPageIsLoaded();
            assertTrue("Main page isn't loaded", mainPage.isOnMainPage());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void LikeTest() {

        try {
            likesPage.clickToLike();
            //  assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void NormalLikeTest() throws InterruptedException {
        //   postOnMainPage.createAndSendPost();
        int likesNumberBefore = likesPage.getLikesNumber();
        Log.info("Getting initial number of likes - " + likesNumberBefore + "");
        Log.info("Verifying that 'like' symbol is unchecked");
        Assert.assertTrue(likesPage.likeUnchecked(), "Like sign in not unchecked before pressing Like button");
        Reporter.log("Like is unchecked before pressing Like button");
        likesPage.clickToLike();
        Thread.sleep(1000);
        int likesNumberAfter = likesPage.getLikesNumber();
        int result = likesNumberAfter - likesNumberBefore;
        Log.info("Getting number of likes after like - " + likesNumberAfter + "");
        Log.info("Checking number of likes added it is - " + result + "");
        Assert.assertEquals(result, 1, "Result of likes added is not 1");
        Reporter.log("One like added after one click");
        Log.info("Verifying that 'like' symbol is checked");
        Assert.assertTrue(likesPage.likeChecked(), "Like sign in not checked after pressing Like button");
        Reporter.log("Like is checked after pressing Like button");

    }

    @Test
    public void addLiketoUserTest() throws InterruptedException {
        // postOnMainPage.createAndSendPost();
        Log.info("Verifying that 'like' symbol is unchecked");
        Assert.assertTrue(likesPage.likeUnchecked(), "Like sign in not unchecked before pressing Like button");
        Reporter.log("Like is unchecked before pressing Like button");
        likesPage.clickToLike();
        Thread.sleep(1000);
        Log.info("Verifying that 'like' symbol is checked");
        Assert.assertTrue(likesPage.likeChecked(), "Like sign in not checked after pressing Like button");
        Reporter.log("Like is checked after pressing Like button");
    }

    @Test
    public void addLikeEndReloudedPageTest() {
        Log.info("Creating new post");
        postOnMainPage.createAndSendPost();
        Log.info("Verifying, that 'Like' sign is Uncheked yet");
        Assert.assertTrue(likesPage.likeUnchecked(), "Like sign is not unchecked before pressing Like button");
        Log.info(" - Like is unchecked before pressing Like button");
        Reporter.log("Like is unchecked before pressing Like button");
        likesPage.clickToLike();
        Assert.assertTrue(likesPage.likeChecked(), "Like sign is not checked after pressing Like button");
        Reporter.log("Like is checked after pressing Like button");
        Log.info("Reloading page...");
        likesPage.reloadPage();
        mainPage.waitUntilMainPageIsLoaded();
        mainPage.isOnMainPage();
        Log.info("... and we are back!");
        Assert.assertTrue(likesPage.likeChecked(), "Like sign is not checked after pressing Like button");
        Log.info(" -Like is still checked after page reloading");
        Reporter.log("Like is still checked after page reloading");
    }

    @Test
    public void pressTheLikeTwiceTest() {
        postOnMainPage.createAndSendPost();
        Log.info("Verifying, that 'Like' sign is Uncheked yet");
        Assert.assertTrue(likesPage.likeUnchecked(), "Like sign is not unchecked before pressing Like button");
        likesPage.clickToLike();
        Assert.assertTrue(likesPage.likeChecked(), "Like sign is not checked after pressing Like button");
        likesPage.clickToLike();
        Assert.assertTrue(likesPage.likeChecked(), "Like sign is not checked after pressing Like button");
        Reporter.log("Like is still checked once after press twice");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}


