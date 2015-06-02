package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MilestoneOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi78 on 5/30/2015.
 */
public class MilestoneOnMainPageTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage milestoneOnMainPage;
    public String someText;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage = PageFactory.initElements(driver, MilestoneOnMainPage.class);

        try {
            loginPage.login("mili@mail.ru", "111111");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMilestonePanel();
            milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SendLanguagePostTest() {

        try {
            milestoneOnMainPage
                    .clickOnLanguageOption()
                    .clickOnSelectItemOption()
                    .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("7")
                    .clickOnMonthOption("5")
                    .fillTextField()
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SendMovementPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnMovementOption()
                    .clickOnSelectMovementItemOption()
                    .clickFirstItemFromMovementList()
                    .clickOnYearsOption("2")
                    .clickOnMonthOption("3")
                    .fillTextField()
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        @Test
    public void SendEatingPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnEatingOption()
                    .clickOnSelectEatingItemOption()
                    .clickFirstItemFromEatingItemList()
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("6")
                    .fillTextField()
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SendToiletingPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnToiletingOption()
                    .clickOnSelectItemToiletingItemOption()
                    .clickFirstItemToiletingItemList()
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("6")
                    .fillTextField()
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SendTreatmentPostTest(){
        try {
            milestoneOnMainPage
                    .clickOnTreatmentOption()
                    .clickOnSelectTreatmentItemOption()
                    .clickFirstItemFromTrratingItemList()
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("6")
                    .fillTextField()
                    .sendPost();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

     @Test
      public  void SendOtherPostTest() {
         try {
             milestoneOnMainPage
                     .clickOnOtherOption()
                     .clickOnSelectOtherItemOption()
                     .fillTextField()
                     .sendPost();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
             //Send Post Milestone Negative Tests

    /* 1)Years:empty
    2)Months:abc
    3)Milestone:empty
    4)Message: Length>2252*/
    @Test
    public void MilestoneNegativeTest1(){
        try {
            someText = randomAlphabetic(22);
            milestoneOnMainPage
                    .clickOnYearsOption("-1")
                    .clickOnMonthOption("-1")
                    .fillTextField()
                    .sendPost();
                  assertTrue(milestoneOnMainPage.alertMessageRequiredFields());
                  assertTrue(milestoneOnMainPage.alertMessageNotValidYear());
                  assertTrue(milestoneOnMainPage.alertMessageNotValidMonth());
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*1)Years:abc
     2)Months:&^$
     3)Milestone:Language:abc
     4)Message:Length:1126*/
    @Test
    public void MilestoneNegativeTest2(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    .clickOnLanguageOption()
                    .clickOnSelectItemOption()
                    .clickOnLanguageItemOption("abc")
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("&^$")
                    .fillTextField()
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* 1)Years:Два
    2)Months:-12
    3)Milestone:Movement:Rolls over
    4)Message:Length:length>2252*/
    @Test
    public void MilestoneNegativeTest3(){
        try {
            someText = randomAlphabetic(4);
            milestoneOnMainPage
                    .clickOnMovementOption()
                    .clickOnSelectItemOption()
                    .clickRollsOverFromMovementList()
                    .clickOnYearsOption("Два")
                    .clickOnMonthOption("2")
                    .fillTextField()
                    .sendPost();
           // assertTrue(milestoneOnMainPage.alertMessageNotValidYear());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:שלושל
    2)Months:-One
    3)Milestone:Eating:Eats with spoon
    4)Message:Length:length=1.*/
    @Test
    public void MilestoneNegativeTest4(){
        try {
            someText = randomAlphabetic(1);
            milestoneOnMainPage
                    .clickOnEatingOption()
                    .clickOnSelectItemOption()
                    .clickEatsWithSpoonFromEatingList()
                    .clickOnYearsOption("שלושל")
                    .clickOnMonthOption("-One")
                    .fillTextField()
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:583687348237560327234686
    2)Months:36
    3)Milestone:empty
    4)Message:Length:length>2252*/
    @Test
    public void MilestoneNegativeTest5(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                 //   .clickOnLanguageOption()
                 //   .clickOnSelectItemOption()
                 //   .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("583687348237560327234686")
                    .clickOnMonthOption("36")
                    .fillTextField()
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:עשרים ואחד
    2)Months:00
    3)Milestone:empty
    4)Message::empty*/
    @Test
    public void MilestoneNegativeTest6(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    //   .clickOnLanguageOption()
                    //   .clickOnSelectItemOption()
                    //   .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("עשרים ואחד")
                    .clickOnMonthOption("00")
                    .fillTextField()
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:-int
    2)Months:16
    3)Milestone:Toileting:dresses alone
    4)Message:Length>2252*/
    @Test
    public void MilestoneNegativeTest7(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                      .clickOnToiletingOption()
                    //   .clickOnSelectItemOption()
                    //   .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("עשרים ואחד")
                    .clickOnMonthOption("00")
                    .fillTextField()
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }




    @AfterClass(alwaysRun = true)
    public void teardown () {
        this.driver.quit();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
