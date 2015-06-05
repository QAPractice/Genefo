package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.WhatWorksOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by alex on 5/29/2015.
 */
public class WhatWorksOnMainTest {

    public enum Option {
        Therapy, Equipment, Nutrition, Exercises,
        Alternative, Other
    }

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public WhatWorksOnMainPage whatWorksOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        whatWorksOnMainPage = PageFactory.initElements(driver, WhatWorksOnMainPage.class);

        try {
            loginPage.login("telrantests@yahoo.com", "12345.com");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                            .openWhatWorksButtonPanel();
            whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }   // We fill data structures, that we defined in whatWorksOnMainPage class.
        whatWorksOnMainPage.defineOptionsLocatorAndItemList();
    }

    @Test
    public void SendPostTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
       // String category = "Therapy";
       // String category = "Equipment";
       // String category = "Nutrition";
       // String category = "Exercises";
        //String category = "Alternative";
        String category = "Other";
        try {
          if(category.equals("Other")) // 'Other' option is different. It has no predetermined list
              whatWorksOnMainPage
                      .clickOnOption(category)
                      .fillItemForOtherOption(otherItem)
                      .clickOnAllStarsTogether()
                      .rateItThree()                //Click on the third star
                      .fillTextField(text)
                      .sendPost();
          else
              whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnItemList()
                    .chooseItemFromItemList(3)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

          sleep(3000); // wait  to see sent post.

            assertTrue(whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue(whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue(whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue(whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            if(category.equals("Other"))
                assertTrue(whatWorksOnMainPage.verifyOtherItemCorrectInSentPost(otherItem));
            else
                assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}
