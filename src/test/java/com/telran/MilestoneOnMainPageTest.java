package com.telran;

import com.telran.pages.DataProviders;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MilestoneOnMainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi, Christina and Mariya on 5/30/2015.
 */
public class MilestoneOnMainPageTest extends TestNgTestBase{

    // private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage milestoneOnMainPage;
    public String age;
    public String month;
    public String year;
    public String post;
    public String textOtherField;
    public String type;
    public String milestone;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage = PageFactory.initElements(driver, MilestoneOnMainPage.class);
        try {
            loginPage.login("mili9@mail.ru", "999999");
            mainPage.waitUntilMainPageIsLoaded()
                    .openMilestonePanel();
            milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        milestoneOnMainPage.fillAllElementsAndItemsToMap();

    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        mainPage.openMainPage();
        mainPage.waitUntilMainPageIsLoaded()
                .openMilestonePanel();
        milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
    }


    @Test(groups = {"smoke", "positive"}, description = "Send Milestone Post", enabled = true, dataProviderClass = DataProviders.class, dataProvider = "loadTypesFromFile")
    public void SendMilestonePostDataDrivenTest(String _type, String _milestone, String _year, String _month) {
        type = _type;
        milestone = _milestone;
        year = _year;
        month = _month;
        age = year + " years " + month + " months";
        post = randomAlphabetic(100);
        Reporter.log("Testing Type: " + type + " , milestone: " + milestone + " , year: " + year + " , month: " + month);
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
            sleep(2000);
            assertTrue("Alert:'Milestone type is not correct'", milestoneOnMainPage.isTypeTrue(type));
            assertTrue("Alert:'Milestone is not correct'", milestoneOnMainPage.isMilestoneTrue(milestone));
            assertTrue("Alert:'The age is not correct'", milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue("Alert:'The text is not correct'", milestoneOnMainPage.isTextCorrect(post));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups = {"smoke", "positive"}, enabled = true)
    public void SendOtherPostTest() {
        type = "Other";
        year = "10";
        month = "5";
        age = year + " years " + month + " months";
        post = randomAlphabetic(500);
        textOtherField = "ABCD";
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .fillOtherField(textOtherField)
                    .clickOnYearsOption(year)
                    .clickOnMonthOption(month)
                    .fillTextField(post)
                    .sendPost()
                    .waitForPostLoaded();
            sleep(2000);
            assertTrue("Alert:'Milestone type is not correct'", milestoneOnMainPage.isTypeTrue(type));
            assertTrue("Other field is not fill", milestoneOnMainPage.isOtherTextCorrect(textOtherField));
            assertTrue("Alert:'The age is not correct'", milestoneOnMainPage.isAgeIsCorrect(age));
            assertTrue("Alert:'The text is not correct'", milestoneOnMainPage.isTextCorrect(post));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Send Post Milestone Negative Tests
    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest1(){
        post = randomAlphabetic(20);
        try {
            milestoneOnMainPage
                    .clickOnMonthOption("A");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared",milestoneOnMainPage.alertMessageRequiredFields());
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest2(){
        type = "Language";
        milestone = "Babbles";
        post = randomAlphabetic(250);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("abc")
                    .clickOnMonthOption("&^$");
            assertTrue("Alert 'Numbers only' for months appeared", milestoneOnMainPage.alertMessageNotValidMonth());
            assertTrue("Alert 'Numbers only' for year appeared", milestoneOnMainPage.alertMessageNotValidYear());
                    milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest3(){
        type = "Movement";
        milestone = "Rolls over";
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("Два")
                    .clickOnMonthOption("-12");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidMonth());
                    milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest4(){
        type = "Daily living";
        milestone = "Eats with spoon";
        post = randomAlphabetic(1);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("שלושל")
                    .clickOnMonthOption("-One");
            assertTrue("Alert 'Numbers only' for year did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            assertTrue("Alert 'Numbers only' for month did not appeared",milestoneOnMainPage.alertMessageNotValidMonth());
                    milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest5(){
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                    .clickOnYearsOption("583687348237560327234686")
                    .clickOnMonthOption("49");
            assertTrue("Alert 'Numbers only' for year did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            assertTrue("Alert 'Numbers only' for month did not appeared",milestoneOnMainPage.alertMessageNotValidMonth());
            milestoneOnMainPage.fillTextField("post")
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared",milestoneOnMainPage.alertMessageRequiredFields());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest6(){
        try {
            milestoneOnMainPage
                    .clickOnYearsOption("עשרים ואחד")
                    .clickOnMonthOption("00");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.sendPost();
            assertTrue("Alert 'Required field' did not appeared",milestoneOnMainPage.alertMessageRequiredFields());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","negative"}, enabled = true)
    public void MilestoneNegativeTest7(){
        type = "Daily living";
        milestone = "Dresses alone";
        post = randomAlphabetic(500);
        try {
            milestoneOnMainPage
                    .clickOnElement(type)
                    .clickOnSelectItemOption()
                    .clickOnElement(milestone)
                    .clickOnYearsOption("-int")
                    .clickOnMonthOption("16");
            assertTrue("Alert 'Numbers only' for month did not appeared", milestoneOnMainPage.alertMessageNotValidYear());
            milestoneOnMainPage.fillTextField(post)
                    .sendPost();
            assertTrue("Alert 'Required field' did not appeared", milestoneOnMainPage.alertMessageRequiredFields());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
     }
    }















