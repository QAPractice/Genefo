package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.WhatWorksOnMainPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.Reporter.log;

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
    @Parameters({"browser"})
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("Firefox"))
        {
            this.driver = new FirefoxDriver();
            log("We are in Firefox browser");
        }
        else if (browser.equalsIgnoreCase("Chrome")) {
            driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
            log("We are in Chrome browser");
        }
        else if (browser.equalsIgnoreCase("InternetExplorer")) {
            driver = TestUtils.chooseDriver(WEB_DRIVER.InternetExplorer);
            log("We are in InternetExplorer browser");
        }
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        whatWorksOnMainPage = PageFactory.initElements(driver, WhatWorksOnMainPage.class);

        try {
            loginPage.login("telrantests@yahoo.com", "12345.com");
            //assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded();
                          // .openWhatWorksButtonPanel();
            //whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }   // We fill data structures, that we defined in whatWorksOnMainPage class.
        whatWorksOnMainPage.defineOptionsLocatorAndItemList();
    }

    @BeforeMethod
    public void beforemethodsetup() {

        mainPage.openMainPage();
        mainPage.waitUntilMainPageIsLoaded()
                            .openWhatWorksButtonPanel();
        whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
    }

// 1
    @Test(groups = {"smoke", "positive"})
    public void SendPostOtherTest() {
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
                    .waitUntilItemFromItemListIsLoaded(3)
                            .chooseItemFromItemList(3)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

          sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            if(category.equals("Other"))
                assertTrue(whatWorksOnMainPage.verifyOtherItemCorrectInSentPost(otherItem));
            else
                assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2
    @Test(groups = {"smoke", "positive"})
    public void SendPostTherapyTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        String category = "Therapy";
        // String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
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
                        .waitUntilItemFromItemListIsLoaded(3)
                        .chooseItemFromItemList(3)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

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

    //3
    @Test(groups = {"smoke", "positive"})
    public void SendPostEquipmentTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        //String category = "Therapy";
        String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
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
                        .waitUntilItemFromItemListIsLoaded(3)
                        .chooseItemFromItemList(3)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

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

    //4
    @Test(groups = {"smoke", "positive"})
    public void SendPostNutritionTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        //String category = "Therapy";
        //String category = "Equipment";
        String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
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
                        .waitUntilItemFromItemListIsLoaded(3)
                        .chooseItemFromItemList(3)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

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


    //5
    @Test(groups = {"smoke", "positive"})
    public void SendPostExercisesTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        //String category = "Therapy";
        //String category = "Equipment";
        //String category = "Nutrition";
        String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
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
                        .waitUntilItemFromItemListIsLoaded(3)
                        .chooseItemFromItemList(3)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

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

    //6
    @Test(groups = {"smoke", "positive"})
    public void SendPostAlternativeTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        //String category = "Therapy";
        //String category = "Equipment";
        //String category = "Nutrition";
        //String category = "Exercises";
        String category = "Alternative";
        //String category = "Other";
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
                        .waitUntilItemFromItemListIsLoaded(3)
                        .chooseItemFromItemList(3)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

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



    // 7
    @Test(groups = {"smoke", "negative"})
    public void EmptyCategoryTest() {
        String text = "My Empty Category Post";
        try {
                whatWorksOnMainPage.clickOnItemList();
            // here we check that we can not choose from the list if our Category is empty
                assertFalse("Item List Is Chosable despite of 'Category' absence",whatWorksOnMainPage.verifyItemListIsChosen() );
                whatWorksOnMainPage
                                .clickOnAllStarsTogether()
                                .rateItThree()                //Click on the third star
                                .fillTextField(text)
                        .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 8
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemOtherTest() {
        String text = "Other: My Empty Category Item Post";
        // String category = "Therapy";
        // String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 9
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemTherapyTest() {
        String text = "Therapy: My Empty Category Item Post";
        String category = "Therapy";
        // String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 10
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemEquipmentTest() {
        String text = "Equipment: My Empty Category Item Post";
        //String category = "Therapy";
        String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 11
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemNutritionTest() {
        String text = "Nutrition: My Empty Category Item Post";
        //String category = "Therapy";
        //String category = "Equipment";
        String category = "Nutrition";
        // String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 12
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemExercisesTest() {
        String text = "Exercises: My Empty Category Item Post";
        //String category = "Therapy";
        //String category = "Equipment";
        //String category = "Nutrition";
        String category = "Exercises";
        //String category = "Alternative";
        //String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 13
    @Test(groups = {"smoke", "negative"})
    public void EmptyListItemAlternativeTest() {
        String text = "Alternative: My Empty Category Item Post";
        //String category = "Therapy";
        //String category = "Equipment";
        //String category = "Nutrition";
        //String category = "Exercises";
        String category = "Alternative";
        //String category = "Other";
        try {
            whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}
