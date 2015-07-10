package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.WhatWorksOnMainPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.apache.log4j.Logger;
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
 * Created by alex on 5/29/2015.
 */
public class WhatWorksOnMainTest {

    // We define variables of enum type to give meaning to numbers 0, 1 and 3
    // And then we use this variables instead of numbers
    public enum Company {
        LAST_ITEM_FROM_LIST(0), FIRST_ITEM_FROM_LIST(1), FOURTH_ITEM_FROM_LIST(4);
        private int value;
        private Company(int value) {
            this.value = value;
        }
    }

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public WhatWorksOnMainPage whatWorksOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) {
        //PropertyConfigurator.configure("log4j.properties");
        if (browser.equalsIgnoreCase("Firefox"))
        {
           this.driver = new FirefoxDriver();
           Log.info("We are in Firefox browser");
        }
        else if (browser.equalsIgnoreCase("Chrome")) {
            driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
            Log.info("We are in Chrome browser");
        }
        else if (browser.equalsIgnoreCase("InternetExplorer")) {
            driver = TestUtils.chooseDriver(WEB_DRIVER.InternetExplorer);
            Log.info("We are in Internet Explorer browser");
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
        whatWorksOnMainPage.defineOptionsLocatorsAndItemList();
    }

    @BeforeMethod
    public void beforemethodsetup() {

        mainPage.openMainPage();
        mainPage.waitUntilMainPageIsLoaded()
                            .openWhatWorksButtonPanel();
        whatWorksOnMainPage.waitUntilWhatWorksPanelIsLoaded();
    }

    @DataProvider
    private Object[][] myProvider(){
        return new Object[][]{
                {"Therapy",     Company.LAST_ITEM_FROM_LIST },// just means that we want to choose the last item from dropdown list
                {"Equipment",   Company.LAST_ITEM_FROM_LIST },
                {"Nutrition",   Company.LAST_ITEM_FROM_LIST },
                {"Exercises",   Company.LAST_ITEM_FROM_LIST },
                {"Alternative", Company.LAST_ITEM_FROM_LIST },
                {"Other",       Company.LAST_ITEM_FROM_LIST },

                {"Therapy",     Company.FIRST_ITEM_FROM_LIST },// just means that we want to choose the first item from dropdown list
                {"Equipment",   Company.FIRST_ITEM_FROM_LIST },
                {"Nutrition",   Company.FIRST_ITEM_FROM_LIST },
                {"Exercises",   Company.FIRST_ITEM_FROM_LIST },
                {"Alternative", Company.FIRST_ITEM_FROM_LIST },
                {"Other",       Company.FIRST_ITEM_FROM_LIST },

                {"Therapy",     Company.FOURTH_ITEM_FROM_LIST }, // just means that we want to choose the fourth item from dropdown list
                {"Equipment",   Company.FOURTH_ITEM_FROM_LIST },
                {"Nutrition",   Company.FOURTH_ITEM_FROM_LIST },
                {"Exercises",   Company.FOURTH_ITEM_FROM_LIST },
                {"Alternative", Company.FOURTH_ITEM_FROM_LIST },
                {"Other",       Company.FOURTH_ITEM_FROM_LIST },
        };
    }

// provider for negative tests where we do not need to choose item from dropdown list
    @DataProvider
    private Object[][] myNegativeProvider(){
        return new Object[][]{
                {"Therapy"},
                {"Equipment"},
                {"Nutrition"},
                {"Exercises"},
                {"Alternative"},
                {"Other"},
             };
    }


    // Click on Category button then choose item from the item list.
    // // Check that you are able to send a post.
    // Category name and item number are given by data provider.
    @Test(groups = {"smoke", "positive"}, dataProvider = "myProvider" )
    public void SendPostTest(String category, Company itemNumber) {
        Date date = new Date();
        String text = "My Post at " + date.toString() ;
        String otherItem = "My Other Item at " + date.toString() ;
        System.out.println("Category " + category + " item number " + itemNumber + "\n");
        try {
            if(category.equals("Other")) // 'Other' option is different. It has no predetermined list
                whatWorksOnMainPage
                        .clickOnOption(category)
                        .fillItemForOtherOption(otherItem)
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();
            else if (itemNumber == Company.LAST_ITEM_FROM_LIST) //We want to choose the last item from the dropdown list
            {   whatWorksOnMainPage
                    .clickOnOption(category)
                    .clickOnItemList()
                    .waitUntilLastItemFromItemListIsLoaded()
                    .chooseLastItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            }
            else            //We want to choose  item  with 'itemNumber' from the dropdown list
                whatWorksOnMainPage
                        .clickOnOption(category)
                        .clickOnItemList()
                        .waitUntilItemFromItemListIsLoaded((itemNumber.value))
                        .chooseItemFromItemList(itemNumber.value)
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

    // Negative test - Do not click on Category button.
    // Check that you are not able to send a post.
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

    // Negative tests - Click on Category button but do not click on item list.
    // Check that you are not able to send a post.
    // Category name is given by data provider.
    @Test(groups = {"smoke", "negative"}, dataProvider = "myNegativeProvider" )
    public void EmptyListItemTest(String category) {
        System.out.println("Category " + category + " Negative test\n");
        Date date = new Date();
        String text = "My Post at " + date.toString();
        try {
            whatWorksOnMainPage
                    .clickOnOption(category);// Here we cover the test that category button should be highlighted after clicking
            assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();
            sleep(2000); // wait  to see sent post.
            assertFalse(whatWorksOnMainPage.verifyTextFromSentPost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    //  1
    @Test(groups = {"smoke", "positive"}, dataProvider = "myProvider" )
    public void SendPostLastItemTest(String category, int itemNumber) {
        Date date = new Date();
        String text = "My Post at " + date.toString() ;
        String otherItem = "Hard work is good for everyone" ;

        try {
            whatWorksOnMainPage.clickOnOption(category);
                    assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnItemList()
                    .waitUntilLastItemFromItemListIsLoaded()
                    .chooseLastItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
/*
    //  2
    @Test(groups = {"smoke", "positive"})
    public void SendPostEquipmentLastItemTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        // String category = "Therapy";
        String category = "Equipment";
        //String category = "Nutrition";
        //String category = "Exercises";
        //String category = "Alternative";
        try {
            whatWorksOnMainPage.clickOnOption(category);
            assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnItemList()
                    .waitUntilLastItemFromItemListIsLoaded()
                    .chooseLastItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  3
    @Test(groups = {"smoke", "positive"})
    public void SendPostNutritionLastItemTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        // String category = "Therapy";
        // String category = "Equipment";
        String category = "Nutrition";
        //String category = "Exercises";
        //String category = "Alternative";
        try {
            whatWorksOnMainPage.clickOnOption(category);
            assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnItemList()
                    .waitUntilLastItemFromItemListIsLoaded()
                    .chooseLastItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //  4
    @Test(groups = {"smoke", "positive"})
    public void SendPostExercisesLastItemTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        // String category = "Therapy";
        // String category = "Equipment";
        // String category = "Nutrition";
        String category = "Exercises";
        //String category = "Alternative";
        try {
            whatWorksOnMainPage.clickOnOption(category);
            assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnItemList()
                    .waitUntilLastItemFromItemListIsLoaded()
                    .chooseLastItemFromItemList()
                    .clickOnAllStarsTogether()
                    .rateItThree()                //Click on the third star
                    .fillTextField(text)
                    .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//  5
    @Test(groups = {"smoke", "positive"})
    public void SendPostAlternativeLastItemTest() {
        String text = "My Fifth Post" ;
        String otherItem = "Hard work is good for everyone" ;
        // String category = "Therapy";
        // String category = "Equipment";
        // String category = "Nutrition";
        // String category = "Exercises";
       String category = "Alternative";
        try {
            whatWorksOnMainPage.clickOnOption(category);
            assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
            whatWorksOnMainPage.clickOnItemList()
                        .waitUntilLastItemFromItemListIsLoaded()
                        .chooseLastItemFromItemList()
                        .clickOnAllStarsTogether()
                        .rateItThree()                //Click on the third star
                        .fillTextField(text)
                        .sendPost();

            sleep(2000); // wait  to see sent post.

            assertTrue("Text in sent post is not correct", whatWorksOnMainPage.verifyTextFromSentPost(text) );
            assertTrue("Category in sent post is not correct", whatWorksOnMainPage.verifyCategoryExistsInSentPost(category));
            assertTrue("Third star is not checked", whatWorksOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue("Forth star is checked",whatWorksOnMainPage.verifyFourthStarNonCheckedInSentPost() );
            assertTrue(whatWorksOnMainPage.verifyListItemCorrectInSentPost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// 6
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
          if(category.equals("Other"))
            {// 'Other' option is different. It has no predetermined list
              whatWorksOnMainPage
                      .clickOnOption(category);
              assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
              whatWorksOnMainPage
                      .fillItemForOtherOption(otherItem)
                      .clickOnAllStarsTogether()
                      .rateItThree()                //Click on the third star
                      .fillTextField(text)
                      .sendPost();
            }
          else {
              whatWorksOnMainPage.clickOnOption(category);
              assertTrue(whatWorksOnMainPage.isOptionHighLighted(category));
              whatWorksOnMainPage.clickOnItemList()
                      .waitUntilItemFromItemListIsLoaded(3)
                      .chooseItemFromItemList(3)
                      .clickOnAllStarsTogether()
                      .rateItThree()                //Click on the third star
                      .fillTextField(text)
                      .sendPost();
              }
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

*/

    /*


        //8
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

        //9
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


        //10
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

        //11
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


    */
        // 12


   /*
        // 14
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

        // 15
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

        // 16
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


        // 17
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


        // 18
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
    */
    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }

}
