package com.telran;

import com.telran.pages.*;
import com.telran.pages.LoginPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by alex on 01/06/2015.
 */
public class PostOnMainTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public PostOnMainPage postOnMainPage;
    private boolean acceptNextAlert = true;


    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("Firefox"))                 this.driver = new FirefoxDriver();
        else if (browser.equalsIgnoreCase("Chrome"))             driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
        else if (browser.equalsIgnoreCase("InternetExplorer"))   driver = TestUtils.chooseDriver(WEB_DRIVER.InternetExplorer);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        postOnMainPage = PageFactory.initElements(driver, PostOnMainPage.class);

        try {
            loginPage.login("telrantests@yahoo.com", "12345.com");
            mainPage.waitUntilMainPageIsLoaded();
                  //  .openPostPanel();
           // postOnMainPage.waitUntilPostPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @BeforeMethod
        public void beforemethodsetup() {

             mainPage.openMainPage();
             mainPage.waitUntilMainPageIsLoaded()
                    .openPostPanel();
             postOnMainPage.waitUntilPostPanelIsLoaded();
    }



    @Test(groups = {"smoke", "positive"})
    public void SendPostSuccessTest() {
        Date date = new Date();
        String text = "My 'Post Category' post at "  + date.toString();

        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();
            sleep(2000);

            assertTrue(mainPage.verifyTextFromSentPost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void SendEmptyPostTest() {
        String text = "" ;

        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();
            sleep(2000);

            assertFalse(mainPage.verifyTextFromSentPost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "negative"})
    public void SendOneLetterPostTest() {
        String text = "A" ;

        try {
            postOnMainPage
                    .fillTextField(text)
                    .sendPost();
            sleep(2000);
            assertFalse(mainPage.verifyTextFromSentPost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterClass(alwaysRun = true)
     public void teardown() {
        this.driver.quit();
    }



}
