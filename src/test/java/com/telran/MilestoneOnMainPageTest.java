package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MilestoneOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi, Christina and Mariya on 5/30/2015.
 */
public class MilestoneOnMainPageTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage milestoneOnMainPage;
    public String someText;
    public String  age;
    public String month;
    public String year;
    public String post;
    public String textOtherField;
    public String type;
    public String milestone;


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
        milestoneOnMainPage.fillAllElementsAndItemsToMap();

    }
    /*@BeforeTest(alwaysRun = true)
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }

    }*/

    @Test(groups={"smoke","positive"})
    public void SendLanguagePostTest() {
        type = "Language";
        milestone = "Smiles";
        year="2";
        month="3";
        age=year+" years "+month+" months";
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                .clickOnElement(type)
                .clickOnSelectItemOption()
                .clickOnElement(milestone)
                .clickOnYearsOption(year)
                .clickOnMonthOption(month)
                .fillTextField(post)
                .sendPost()
                .waitForPostLoaded();
                 sleep(3000);
        assertTrue(milestoneOnMainPage.isTypeTrue(type));
        assertTrue(milestoneOnMainPage.isMilestoneTrue(milestone));
        assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
        assertTrue(milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","positive"})
    public void SendMovementPostTest() {
        type="Movement";
        milestone="Holds head";
        year="40";
        month="15";
        age=year+" years "+month+" months";
        post = randomAlphabetic(50);
        try {
        milestoneOnMainPage
                .clickOnElement(type)
                .clickOnSelectItemOption()
                .clickOnElement(milestone)
                .clickOnYearsOption(year)
                .clickOnMonthOption(month)
                .fillTextField(post)
                .sendPost()
                .waitForPostLoaded();
                 sleep(3000);
        assertTrue(milestoneOnMainPage.isTypeTrue(type));
        assertTrue(milestoneOnMainPage.isMilestoneTrue(milestone));
        assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
        assertTrue(milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(groups={"smoke","positive"})
    public void SendEatingPostTest() {
        type="Eating";
        milestone="Holds bottle";
        year="0";
        month="0";
        age=year+" years "+month+" months";
        post = randomAlphabetic(250);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
                     sleep(3000);
            assertTrue(milestoneOnMainPage.isTypeTrue(type));
            assertTrue(milestoneOnMainPage.isMilestoneTrue(milestone));
            assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue(milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups={"smoke","positive"})
    public void SendToiletingPostTest() {
        type = "Toileting";
        milestone = "Toilet trained";
        year="100";
        month="38";
        age=year+" years "+month+" months";
        post = randomAlphabetic(1);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(3000);
            assertTrue(milestoneOnMainPage.isTypeTrue(type));
            assertTrue(milestoneOnMainPage.isMilestoneTrue(milestone));
            assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue(milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups={"smoke","positive"})
    public void SendTreatmentPostTest(){
        type = "Treatment";
        milestone = "Surgery";
        year="1";
        month="17";
        age=year+" years "+month+" months";
        post = randomAlphabetic(3);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(3000);
            assertTrue(milestoneOnMainPage.isTypeTrue(type));
            assertTrue(milestoneOnMainPage.isMilestoneTrue(milestone));
            assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue(milestoneOnMainPage.isTextCorrect(post));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","positive"})
    public  void SendOtherPostTest() {
        type = "Other";
        year="10";
        month="5";
        age=year+" years "+month+" months";
        post = randomAlphabetic(500);
        textOtherField="ABCD";
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectOtherItemOption()
                    .fillOtherField(textOtherField)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(3000);
            assertTrue(milestoneOnMainPage.isTypeTrue(type));
            assertTrue(milestoneOnMainPage.isOtherTextCorrect(textOtherField));
            assertTrue(milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue(milestoneOnMainPage.isTextCorrect(post));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Send Post Milestone Negative Tests

    /* 1)Years:empty
    2)Months:
    3)Milestone:empty
    4)Message: Length>2252*/
    @Test(enabled = false)
    public void MilestoneNegativeTest1(){
        type = "Movement";
        milestone = "  ";
        try {
            someText = randomAlphabetic(3);
            milestoneOnMainPage
                    .clickOnYearsOption("")
                    .clickOnMonthOption("-1")
                    .fillTextField("someText")
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
    @Test(enabled = false)
    public void MilestoneNegativeTest2(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    .clickOnLanguageOption()
                    .clickOnSelectItemOption()
                    .clickOnLanguageItemOption("abc")
                    .clickOnYearsOption("abc")
                    .clickOnMonthOption("&^$")
                    .fillTextField(someText)
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* 1)Years:Два
    2)Months:-12
    3)Milestone:Movement:Rolls over
    4)Message:Length:length>2252*/
    @Test(enabled = false)
    public void MilestoneNegativeTest3(){
        try {
            someText = randomAlphabetic(4);
            milestoneOnMainPage
                    .clickOnMovementOption()
                    .clickOnSelectItemOption()
                    .clickRollsOverFromMovementList()
                    .clickOnYearsOption("Два")
                    .clickOnMonthOption("2")
                    .fillTextField("")
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:שלושל
    2)Months:-One
    3)Milestone:Eating:Eats with spoon
    4)Message:Length:length=1.*/
    @Test(enabled = false)
    public void MilestoneNegativeTest4(){
        try {
            someText = randomAlphabetic(1);
            milestoneOnMainPage
                    .clickOnEatingOption()
                    .clickOnSelectItemOption()
                    .clickEatsWithSpoonFromEatingList()
                    .clickOnYearsOption("שלושל")
                    .clickOnMonthOption("-One")
                    .fillTextField("")
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:583687348237560327234686
    2)Months:36
    3)Milestone:empty
    4)Message:Length:length>2252*/
    @Test(enabled = false)
    public void MilestoneNegativeTest5(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    .clickOnYearsOption("583687348237560327234686")
                    .clickOnMonthOption("36")
                    .fillTextField("")
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:עשרים ואחד
    2)Months:00
    3)Milestone:empty
    4)Message::empty*/
    @Test(enabled = false)
    public void MilestoneNegativeTest6(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    //   .clickOnLanguageOption()
                    //   .clickOnSelectItemOption()
                    //   .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("עשרים ואחד")
                    .clickOnMonthOption("00")
                    .fillTextField("")
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:-int
    2)Months:16
    3)Milestone:Toileting:dresses alone
    4)Message:Length>2252*/
    @Test(enabled = false)
    public void MilestoneNegativeTest7(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    .clickOnToiletingOption()
                    //   .clickOnSelectItemOption()
                    //   .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("עשרים ואחד")
                    .clickOnMonthOption("00")
                    .fillTextField("")
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*1)Years:-int
    2)Months:16
    3)Milestone:Toileting:dresses alone
    4)Message:Length>2252*/






    @AfterClass(alwaysRun = true)
    public void teardown () {
        this.driver.quit();
    }

}













