package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MedicineOnMainPage;
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
 * Created by Marina on 6/1/2015.
 */
public class MedicineOnMainPageTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MedicineOnMainPage medicineOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver(); //new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        medicineOnMainPage = PageFactory.initElements(driver, MedicineOnMainPage.class);

        try {
            loginPage.login("stritenko@gmail.com", "111111");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMedicinePanel();
            medicineOnMainPage.isOnMedicinePanel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Positive tests

    @Test(groups = {"smoke", "positive"})
    public void sendMedicineWithShortMedNameReasonTest() {
        String text = "take with food or milk";
        String shortName = "adv";
        String fullName = "advil";
        String shortReason = "hea";
        String fullReason = "headache";

        try {
            medicineOnMainPage
                    .fillExistingNameOfMedicine(shortName, fullName)
                    .fillExistingReasonForMedicine(shortReason, fullReason)
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(mainPage.verifyTextFromSentPost(text));
            assertTrue(medicineOnMainPage.verifyNewNameFromSentPost(fullName));
            assertTrue(medicineOnMainPage.verifyNewReasonFromSentPost(fullReason));
            assertTrue(medicineOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue(medicineOnMainPage.verifyFourthStarNonCheckedInSentPost());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"smoke", "positive"})
    public void sendMedicineWithFullMedNameReasonTest() {
        String text = "take with food or milk";
        String newName = "ascorbic acid";
        String newReason = "enervation";

        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(newName)
                    .fillNewReasonForMedicine(newReason)
                    .clickOnAllStarsTogether()
                    .rateOneStar()            //Click on the first star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(mainPage.verifyTextFromSentPost(text));
            assertTrue(medicineOnMainPage.verifyNewNameFromSentPost(newName));
            assertTrue(medicineOnMainPage.verifyNewReasonFromSentPost(newReason));
            assertTrue(medicineOnMainPage.verifyFirstStarCheckedInSentPost());
            assertTrue(medicineOnMainPage.verifySecondStarNonCheckedInSentPost());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void sendMedicineWithShortUpperKeyMedNameReasonTest() {
        String text = "TAKE WITH FOOD OR MILK";
        String shortName = "ADV";
        String fullName = "advil";
        String shortReason = "HEA";
        String fullReason = "headache";

        try {
            medicineOnMainPage
                    .fillExistingNameOfMedicine(shortName, fullName)
                    .fillExistingReasonForMedicine(shortReason, fullReason)
                    .clickOnAllStarsTogether()
                    .rateFifeStars()            //Click on the fifth star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(mainPage.verifyTextFromSentPost(text));
            assertTrue(medicineOnMainPage.verifyNewNameFromSentPost(fullName));
            assertTrue(medicineOnMainPage.verifyNewReasonFromSentPost(fullReason));
            assertTrue(medicineOnMainPage.verifyFifthStarCheckedInSentPost());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void sendMedicineWithFullUpperKeyMedNameReasonTest() {
        String text = "take with food or milk";
        String newName = "VALERIAN";
        String newReason = "NERVOSITY";

        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(newName)
                    .fillNewReasonForMedicine(newReason)
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(mainPage.verifyTextFromSentPost(text));
            assertTrue(medicineOnMainPage.verifyNewNameFromSentPost(newName));
            assertTrue(medicineOnMainPage.verifyNewReasonFromSentPost(newReason));
            assertTrue(medicineOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue(medicineOnMainPage.verifyFourthStarNonCheckedInSentPost());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"})
    public void sendMedicineWithSpecialCharactersTest() {
        String text = "~`!@#$%^&*()_+<>?:\"{}[];’";
        String newName = "~`!@#$%^&*()_+<>?:\"{}[];’";
        String newReason = "~`!@#$%^&*()_+<>?:\"{}[];’";

        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(newName)
                    .fillNewReasonForMedicine(newReason)
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(text)
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(mainPage.verifyTextFromSentPost(text));
            assertTrue(medicineOnMainPage.verifyNewNameFromSentPost(newName));
            assertTrue(medicineOnMainPage.verifyNewReasonFromSentPost(newReason));
            assertTrue(medicineOnMainPage.verifyThirdStarCheckedInSentPost());
            assertTrue(medicineOnMainPage.verifyFourthStarNonCheckedInSentPost());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Negative tests

    @Test(groups = {"smoke", "negative"})
    public void sendMedicineWithBlankFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewReasonForMedicine(" ")
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);

            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Rating with blank mandatory field
    @Test(groups = {"smoke", "negative"})
    public void sendMedicineRatingWithBlankMandatoryFieldTest() {


        try {
            medicineOnMainPage
                    .fillNewNameOfMedicine(" ")
                    .fillNewReasonForMedicine(" ")
                    .clickOnAllStarsTogether()
                    .rateThreeStars()             //Click on the third star
                    .typeTellUsMore(" ")
                    .clickOnPostButton();
            sleep(3000);


            assertTrue(medicineOnMainPage.alertErrorMessageRequiredFields());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }


}

